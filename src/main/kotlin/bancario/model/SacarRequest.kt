package org.example.bancario.model

import kotlinx.serialization.Serializable

@Serializable
data class SacarRequest(
    val tipo: String = "Saque",
    val conta: Int,
    val valor: Int
)