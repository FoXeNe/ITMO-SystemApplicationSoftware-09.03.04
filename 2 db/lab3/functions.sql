CREATE EXTENSION IF NOT EXISTS plpython3u;
CREATE EXTENSION IF NOT EXISTS plperl;

-- fib
CREATE OR REPLACE FUNCTION fib_plpgsql(n INT) RETURNS BIGINT
LANGUAGE plpgsql AS $$
DECLARE a BIGINT := 0; b BIGINT := 1; tmp BIGINT; i INT;
BEGIN
    IF n <= 0 THEN RETURN 0; END IF;
    FOR i IN 2..n LOOP
        tmp := a + b; a := b; b := tmp;
    END LOOP;
    RETURN b;
END;
$$;

CREATE OR REPLACE FUNCTION fib_python(n INT) RETURNS BIGINT
LANGUAGE plpython3u AS $$
if n <= 0: return 0
a, b = 0, 1
for i in range(n - 1):
    a, b = b, a + b
return b
$$;

CREATE OR REPLACE FUNCTION fib_perl(n INT) RETURNS BIGINT
LANGUAGE plperl AS $$
my $n = shift;
return 0 if $n <= 0;
my ($a, $b) = (0, 1);
for my $i (2..$n) { ($a, $b) = ($b, $a + $b); }
return $b;
$$;

-- danger
CREATE OR REPLACE FUNCTION danger_plpgsql(city_id_param INT) RETURNS BIGINT
LANGUAGE plpgsql AS $$
DECLARE result BIGINT;
BEGIN
    SELECT COALESCE(SUM(i.bite_size * CASE WHEN c.is_venomus THEN 2 ELSE 1 END), 0)
    INTO result
    FROM incidents i
    JOIN creatures c ON i.creature_id = c.id
    JOIN people    p ON i.bitten_id   = p.people_id
    WHERE p.city_id = city_id_param;
    RETURN result;
END;
$$;

CREATE OR REPLACE FUNCTION danger_python(city_id_param INT) RETURNS BIGINT
LANGUAGE plpython3u AS $$
    result = plpy.execute(
        "SELECT COALESCE(SUM(i.bite_size * CASE WHEN c.is_venomus THEN 2 ELSE 1 END), 0) AS s"
        " FROM incidents i"
        " JOIN creatures c ON i.creature_id = c.id"
        " JOIN people    p ON i.bitten_id   = p.people_id"
        " WHERE p.city_id = %d" % city_id_param
    )
    return result[0]["s"]
$$;

CREATE OR REPLACE FUNCTION danger_perl(city_id_param INT) RETURNS BIGINT
LANGUAGE plperl AS $$
    my $cid = int(shift);
    my $rv = spi_exec_query(
        "SELECT COALESCE(SUM(i.bite_size * CASE WHEN c.is_venomus THEN 2 ELSE 1 END), 0) AS s
         FROM incidents i
         JOIN creatures c ON i.creature_id = c.id
         JOIN people    p ON i.bitten_id   = p.people_id
         WHERE p.city_id = $cid"
    );
    return $rv->{rows}[0]{s};
$$;
