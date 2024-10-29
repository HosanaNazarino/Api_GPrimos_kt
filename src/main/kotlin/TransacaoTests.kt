package org.example.bancario.tests

import kotlinx.coroutines.*
import org.example.bancario.repository.BancoRepository


fun main() = runBlocking {
    BancoRepository.criarConta(123, 500)
    BancoRepository.criarConta(456, 300)

    val coroutines = List(100) { index ->
        GlobalScope.launch {
            if (index % 2 == 0) {
                BancoRepository.depositar(123, 10)
            } else {
                BancoRepository.sacar(123, 10)
            }
        }
    }

    coroutines.forEach { it.join() }

    val conta123 = BancoRepository.obterConta(123)
    println("Saldo final da conta 123: ${conta123?.saldo}")
}
