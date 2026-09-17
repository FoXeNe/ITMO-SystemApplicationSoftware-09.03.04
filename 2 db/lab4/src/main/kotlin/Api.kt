import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.double
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive

suspend fun fetchPrices(): Map<String, Double> {
    val client =
        HttpClient(CIO) {
            install(ContentNegotiation) { json() }
        }

    val resp =
        client.get("https://api.coingecko.com/api/v3/simple/price") {
            parameter("ids", "bitcoin,ethereum,dogecoin")
            parameter("vs_currencies", "usd")
        }
    client.close()

    val body = resp.body<JsonObject>()
    return body.mapValues { (_, v) -> v.jsonObject["usd"]!!.jsonPrimitive.double }
}
