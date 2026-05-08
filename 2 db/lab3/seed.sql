ALTER TABLE people    ADD COLUMN IF NOT EXISTS bio   TEXT;
ALTER TABLE incidents ADD COLUMN IF NOT EXISTS notes TEXT;

INSERT INTO cities (name, planet_id)
SELECT 'City_' || i, (i % 6) + 1
FROM generate_series(1, 200000) AS gs(i);

INSERT INTO people (name, is_bitten, city_id, bio)
SELECT
    'Person_' || i,
    (i % 7 = 0),
    (i % 200007) + 1,
    md5(i::text) || md5((i*3)::text) || md5((i*7)::text) || md5((i*11)::text)
FROM generate_series(1, 3000000) AS gs(i);

INSERT INTO incidents (creature_id, bitten_id, bite_size, location, notes)
SELECT
    (i % 8) + 1,
    (i % 3000006) + 1,
    (i % 28) + 1,
    'Location_' || (i % 1000),
    md5(i::text) || md5((i*2)::text) || md5((i*5)::text) || md5((i*13)::text) || md5((i*17)::text)
FROM generate_series(1, 4000000) AS gs(i);

CREATE INDEX ON people(city_id);
CREATE INDEX ON incidents(creature_id);
CREATE INDEX ON incidents(bitten_id);

ANALYZE;
