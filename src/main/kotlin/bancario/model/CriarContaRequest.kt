package org.example.bancario.model

import kotlinx.serialization.Serializable

@Serializable
data class CriarContaRequest(
    val tipo: String = "Abertura de Conta",
    val conta: Int,
    val saldoInicial: Int
)

