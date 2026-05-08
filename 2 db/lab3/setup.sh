#!/usr/bin/env bash
export PGPASSWORD=123

echo "=== Запуск нативного ==="
sudo systemctl start postgresql
sleep 2

sudo -u postgres psql -q -c "ALTER ROLE postgres PASSWORD '123';"
sudo -u postgres psql -q -c "DROP DATABASE IF EXISTS labdb;"
sudo -u postgres psql -q -c "CREATE DATABASE labdb OWNER postgres;"

echo "Нативный PostgreSQL готов"

echo ""
echo "=== Запуск Docker ==="
sudo systemctl start docker
sudo docker compose up -d --build
echo "Ждём старта контейнера..."
until pg_isready -h localhost -p 5433 -U postgres -q; do sleep 1; done
echo "Docker PostgreSQL готов (порт 5433)"

echo ""
echo "=== Схема БД ==="

NATIVE="psql -h localhost -p 5432 -U postgres -d labdb"
DOCKER="psql -h localhost -p 5433 -U postgres -d labdb"

$NATIVE -f main.sql
$DOCKER -f main.sql

echo ""
echo "=== Засидка данных ==="
echo "Нативный..."
$NATIVE -f seed.sql
echo "Docker..."
$DOCKER -f seed.sql

echo ""
echo "=== Функции на разных языках ==="

$DOCKER -f functions.sql

sudo -u postgres psql -q -d labdb <<'SQL'
CREATE EXTENSION IF NOT EXISTS plpython3u;
CREATE EXTENSION IF NOT EXISTS plperl;
SQL
sudo -u postgres psql -q -d labdb -f functions.sql
sudo -u postgres psql -q -d labdb -c "
  GRANT EXECUTE ON ALL FUNCTIONS IN SCHEMA public TO postgres;
  GRANT EXECUTE ON ALL PROCEDURES IN SCHEMA public TO postgres;
"

echo -n "  Native: "
$NATIVE -t -c "SELECT pg_size_pretty(pg_database_size('labdb'));" | xargs
echo -n "  Docker: "
$DOCKER -t -c "SELECT pg_size_pretty(pg_database_size('labdb'));" | xargs
