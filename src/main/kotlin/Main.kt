package org.example

import org.example.bancario.api.BancoApi
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.routing.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import org.slf4j.LoggerFactory

val logger = LoggerFactory.getLogger("MainKt")

fun main() {
    logger.info("Iniciando SERVIDOR ...")

    embeddedServer(Netty, port = 8080) {
        install(ContentNegotiation) {
            json()
        }
        routing {
            BancoApi()
        }
    }.start(wait = true)

    logger.info("SERVIDOR iniciado.")
}
