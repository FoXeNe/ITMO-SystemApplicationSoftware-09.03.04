import kotlinx.coroutines.runBlocking
import java.sql.DriverManager

fun main() =
    runBlocking {
        val conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)

        setup(conn)

        val prices = fetchPrices()
        val since = java.sql.Timestamp(System.currentTimeMillis())
        insert(conn, prices)
        printChanges(conn, since)

        conn.close()
    }
