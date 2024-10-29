package org.example.bancario.model

import kotlinx.serialization.Serializable

@Serializable
data class DepositarRequest(
    val tipo: String = "Depósito",
    val conta: Int,
    val valor: Int
)
