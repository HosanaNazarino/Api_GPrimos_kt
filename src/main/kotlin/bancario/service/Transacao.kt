package org.example.bancario.service

import org.example.bancario.model.*
import org.example.bancario.repository.BancoRepository

class Transacao {
    fun criarConta(conta: Int, saldoInicial: Int): Conta {
        return BancoRepository.criarConta(conta, saldoInicial)
    }

    fun obterConta(conta: Int): Conta? {
        return BancoRepository.obterConta(conta)
    }

    fun depositar(conta: Int, valor: Int) {
        BancoRepository.depositar(conta, valor)
    }

    fun sacar(conta: Int, valor: Int) {
        BancoRepository.sacar(conta, valor)
    }

    fun transferir(contaOrigem: Int, contaDestino: Int, valor: Int) {
        BancoRepository.transferir(contaOrigem, contaDestino, valor)
    }
}
