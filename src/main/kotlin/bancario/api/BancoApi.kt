package org.example.bancario.api

import bancario.model.TransferirRequest
import org.example.bancario.model.*
import org.example.bancario.service.Transacao
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.request.*

fun Route.BancoApi() {
    val service = Transacao()

    post("/criarconta") {
        val request = call.receive<CriarContaRequest>()
        service.criarConta(request.conta, request.saldoInicial)
        call.respond(HttpStatusCode.Created, mapOf("tipo" to request.tipo, "message" to "CONTA CRIADA COM SUCESSO."))
    }

    get("/consultarconta/{numero}") {
        val numero = call.parameters["numero"]?.toIntOrNull()
        if (numero != null) {
            val conta = service.obterConta(numero)
            if (conta != null) {
                call.respond(HttpStatusCode.OK, conta)
            } else {
                call.respond(HttpStatusCode.NotFound, "CONTA NÃO ENCONTRADA.")
            }
        } else {
            call.respond(HttpStatusCode.BadRequest, "NÚMERO DA CONTA INVÁLIDO.")
        }
    }

    post("/depositar") {
        val request = call.receive<DepositarRequest>()
        service.depositar(request.conta, request.valor)
        call.respond(HttpStatusCode.OK, mapOf("tipo" to request.tipo, "message" to "DEPÓSITO REALIZADO COM SUCESSO."))
    }

    post("/sacar") {
        val request = call.receive<SacarRequest>()
        try {
            service.sacar(request.conta, request.valor)
            call.respond(HttpStatusCode.OK, mapOf("tipo" to request.tipo, "message" to "SAQUE REALIZADO COM SUCESSO."))
        } catch (e: Exception) {
            call.respond(HttpStatusCode.BadRequest, e.message ?: "ERRO, SAQUE NÃO REALIZADO.")
        }
    }

    post("/transferir") {
        val request = call.receive<TransferirRequest>()
        try {
            service.transferir(request.contaOrigem, request.contaDestino, request.valor)
            call.respond(HttpStatusCode.OK, mapOf("tipo" to request.tipo, "message" to "TRANSFERÊNCIA REALIZADA COM SUCESSO."))
        } catch (e: Exception) {
            call.respond(HttpStatusCode.BadRequest, e.message ?: "ERRO, TRANSFERÊNCIA NÃO REALIZADA.")
        }
    }
}
