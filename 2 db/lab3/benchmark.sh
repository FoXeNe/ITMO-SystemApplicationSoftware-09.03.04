#!/usr/bin/env bash
export PGPASSWORD=123
export LANG=C

NATIVE="psql -U postgres -d labdb"
DOCKER="psql -U postgres -d labdb"

echo -n "  Native: "
$NATIVE -t -c "SELECT pg_size_pretty(pg_database_size('labdb'));" 2>/dev/null | xargs
echo -n "  Docker: "
$DOCKER -t -c "SELECT pg_size_pretty(pg_database_size('labdb'));" 2>/dev/null | xargs

echo ""

Q1="SELECT pl.name, ci.name, c.name, COUNT(*), SUM(i.bite_size)
    FROM incidents i
    JOIN people p    ON i.bitten_id   = p.people_id
    JOIN cities ci   ON p.city_id     = ci.id
    JOIN planets pl  ON ci.planet_id  = pl.id
    JOIN creatures c ON i.creature_id = c.id
    GROUP BY pl.name, ci.name, c.name
    ORDER BY SUM(i.bite_size) DESC LIMIT 20;"

Q2="SELECT ci.name, COUNT(p.people_id) AS population,
           SUM(CASE WHEN p.is_bitten THEN 1 ELSE 0 END) AS bitten,
           RANK() OVER (ORDER BY COUNT(p.people_id) DESC) AS rank
    FROM cities ci
    LEFT JOIN people p ON p.city_id = ci.id
    GROUP BY ci.name
    ORDER BY population DESC LIMIT 30;"

Q3="WITH stats AS (
      SELECT p.city_id,
             COUNT(*) AS incidents,
             AVG(i.bite_size) AS avg_bite
      FROM incidents i
      JOIN people p ON i.bitten_id = p.people_id
      GROUP BY p.city_id
    )
    SELECT ci.name, s.incidents, ROUND(s.avg_bite::numeric, 2)
    FROM stats s
    JOIN cities ci ON ci.id = s.city_id
    WHERE s.incidents > (SELECT AVG(incidents) FROM stats)
    ORDER BY s.incidents DESC LIMIT 20;"

echo "=== JOIN ==="
t_n=$($NATIVE -c '\timing on' -c "$Q1" 2>&1 | grep "Time:" | awk '{print $2}')
t_d=$($DOCKER -c '\timing on' -c "$Q1" 2>&1 | grep "Time:" | awk '{print $2}')
echo "  Native: ${t_n} ms"
echo "  Docker: ${t_d} ms"

echo "=== RANK ==="
t_n=$($NATIVE -c '\timing on' -c "$Q2" 2>&1 | grep "Time:" | awk '{print $2}')
t_d=$($DOCKER -c '\timing on' -c "$Q2" 2>&1 | grep "Time:" | awk '{print $2}')
echo "  Native: ${t_n} ms"
echo "  Docker: ${t_d} ms"

echo "=== WITH ==="
t_n=$($NATIVE -c '\timing on' -c "$Q3" 2>&1 | grep "Time:" | awk '{print $2}')
t_d=$($DOCKER -c '\timing on' -c "$Q3" 2>&1 | grep "Time:" | awk '{print $2}')
echo "  Native: ${t_n} ms"
echo "  Docker: ${t_d} ms"

echo ""
echo "=== pgbench ==="

echo "Инициализация таблиц pgbench..."
pgbench -h localhost -p 5432 -U postgres -i -s 5 labdb 2>&1 | tail -1
pgbench -h localhost -p 5433 -U postgres -i -s 5 labdb 2>&1 | tail -1

echo "Native:"
pgbench -h localhost -p 5432 -U postgres -c 8 -j 4 -T 30 labdb 2>/dev/null | grep -E "tps|latency average"

echo "Docker:"
pgbench -h localhost -p 5433 -U postgres -c 8 -j 4 -T 30 labdb 2>/dev/null | grep -E "tps|latency average"

echo ""
echo "=== Сравнение языков fib ==="

for lang in plpgsql python perl; do
  q="SELECT SUM(fib_${lang}(30)) FROM generate_series(1, 10000);"
  t_n=$($NATIVE -c '\timing on' -c "$q" 2>&1 | grep "Time:" | awk '{print $2}')
  t_d=$($DOCKER -c '\timing on' -c "$q" 2>&1 | grep "Time:" | awk '{print $2}')
  echo "$lang:"
  echo "  Native: ${t_n} ms"
  echo "  Docker: ${t_d} ms"
done

echo ""
echo "=== Сравнение языков danger_score ==="

for lang in plpgsql python perl; do
  q="SELECT SUM(danger_${lang}((random()*200006+1)::int)) FROM generate_series(1, 5000);"
  t_n=$($NATIVE -c '\timing on' -c "$q" 2>&1 | grep "Time:" | awk '{print $2}')
  t_d=$($DOCKER -c '\timing on' -c "$q" 2>&1 | grep "Time:" | awk '{print $2}')
  echo "$lang:"
  echo "  Native: ${t_n:-N/A} ms"
  echo "  Docker: ${t_d:-N/A} ms"
done
