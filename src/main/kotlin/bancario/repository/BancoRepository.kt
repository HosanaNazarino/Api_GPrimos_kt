package org.example.bancario.repository

import org.example.bancario.model.Conta
import java.util.concurrent.locks.ReentrantLock

object BancoRepository {
    private val contas = mutableMapOf<Int, Conta>()
    private val lock = ReentrantLock()

    fun criarConta(numero: Int, saldoInicial: Int): Conta {
        lock.lock()
        return try {
            val conta = Conta(numero, saldoInicial)
            contas[numero] = conta
            logTransacao("Criar Conta", numero, saldoInicial, saldoInicial)
            conta
        } finally {
            lock.unlock()
        }
    }

    fun obterConta(numero: Int): Conta? {
        lock.lock()
        return try {
            contas[numero]
        } finally {
            lock.unlock()
        }
    }

    fun depositar(numero: Int, valor: Int) {
        lock.lock()
        try {
            contas[numero]?.let {
                it.saldo += valor
                logTransacao("Depósito", numero, valor, it.saldo)
            }
        } finally {
            lock.unlock()
        }
    }

    fun sacar(numero: Int, valor: Int) {
        lock.lock()
        try {
            val conta = contas[numero]
            if (conta != null && conta.saldo >= valor) {
                conta.saldo -= valor
                logTransacao("Saque", numero, valor, conta.saldo)
            } else {
                throw Exception("Saldo insuficiente.")
            }
        } finally {
            lock.unlock()
        }
    }

    fun transferir(contaOrigem: Int, contaDestino: Int, valor: Int) {
        lock.lock()
        try {
            sacar(contaOrigem, valor)
            depositar(contaDestino, valor)
            logTransacao("Transferência", contaOrigem, valor, contas[contaOrigem]?.saldo ?: 0)
            logTransacao("Transferência", contaDestino, valor, contas[contaDestino]?.saldo ?: 0)
        } finally {
            lock.unlock()
        }
    }

    private fun logTransacao(tipo: String, numero: Int, valor: Int, saldo: Int) {
        println("Transação: $tipo | Conta: $numero | Valor: $valor | Saldo Atual: $saldo")
    }
}
