package org.example.bancario.model

import kotlinx.serialization.Serializable

@Serializable
data class Conta(
    val conta: Int,
    var saldo: Int
)

