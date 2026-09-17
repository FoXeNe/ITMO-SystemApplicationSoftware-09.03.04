import java.sql.Connection
import java.sql.DriverManager

const val DB_URL = "jdbc:postgresql://localhost:5432/lab4"
const val DB_USER = "postgres"
const val DB_PASS = "postgres"

fun setup(conn: Connection) {
    val d = "$"
    conn.createStatement().use { s ->
        s.execute(
            """
            CREATE TABLE IF NOT EXISTS prices (
                id       SERIAL PRIMARY KEY,
                coin     TEXT    NOT NULL,
                price    NUMERIC NOT NULL,
                ts       TIMESTAMP DEFAULT NOW()
            )
        """,
        )

        s.execute(
            """
            CREATE TABLE IF NOT EXISTS price_changes (
                id      SERIAL PRIMARY KEY,
                coin    TEXT    NOT NULL,
                old_p   NUMERIC,
                new_p   NUMERIC,
                delta   NUMERIC,
                ts      TIMESTAMP DEFAULT NOW()
            )
        """,
        )

        s.execute(
            """
            CREATE OR REPLACE FUNCTION track_change() RETURNS TRIGGER AS ${d}${d}
            DECLARE
                prev NUMERIC;
            BEGIN
                SELECT price INTO prev
                FROM prices
                WHERE coin = NEW.coin AND id < NEW.id
                ORDER BY id DESC
                LIMIT 1;

                IF prev IS NOT NULL THEN
                    INSERT INTO price_changes (coin, old_p, new_p, delta)
                    VALUES (
                        NEW.coin,
                        prev,
                        NEW.price,
                        ROUND((NEW.price - prev) / prev * 100, 4)
                    );
                END IF;

                RETURN NEW;
            END;
            ${d}${d} LANGUAGE plpgsql;
        """,
        )

        s.execute("DROP TRIGGER IF EXISTS price_insert_trigger ON prices")
        s.execute(
            """
            CREATE TRIGGER price_insert_trigger
            AFTER INSERT ON prices
            FOR EACH ROW EXECUTE FUNCTION track_change()
        """,
        )
    }
}

fun insert(
    conn: Connection,
    prices: Map<String, Double>,
) {
    conn.prepareStatement("INSERT INTO prices (coin, price) VALUES (?, ?)").use { ps ->
        for ((coin, price) in prices) {
            ps.setString(1, coin)
            ps.setDouble(2, price)
            ps.execute()
            println("$coin = $price")
        }
    }
}

fun printChanges(conn: Connection, since: java.sql.Timestamp) {
    val rs =
        conn
            .prepareStatement(
                """
        SELECT coin, old_p, new_p, delta, ts
        FROM price_changes
        WHERE ts >= ?
        ORDER BY ts DESC
        LIMIT 15
      """,
            ).apply { setTimestamp(1, since) }
            .executeQuery()

    println("изменение цены")
    var empty = true
    while (rs.next()) {
        empty = false
        val coin = rs.getString("coin")
        val old = rs.getDouble("old_p")
        val new = rs.getDouble("new_p")
        val d = rs.getDouble("delta")
        println("$coin: $old -> $new  (${if (d >= 0) "+" else ""}$d%)")
    }

    if (empty) println("нет изменений")
}
