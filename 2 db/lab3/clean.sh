#!/usr/bin/env bash
export PGPASSWORD=123

NATIVE="psql -h localhost -p 5432 -U postgres -d labdb"
DOCKER="psql -h localhost -p 5433 -U postgres -d labdb"

SQL='
DROP TABLE IF EXISTS library_records, bioservice_record, incidents, people, cities, creatures, planets, bioservice_db, library CASCADE;
DROP FUNCTION IF EXISTS mark_bitten() CASCADE;
'

$NATIVE -c "$SQL"
$DOCKER -c "$SQL"
