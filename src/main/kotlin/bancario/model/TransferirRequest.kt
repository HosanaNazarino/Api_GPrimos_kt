package bancario.model

import kotlinx.serialization.Serializable

@Serializable
data class TransferirRequest(
    val tipo: String = "Transferência",
    val contaOrigem: Int,
    val contaDestino: Int,
    val valor: Int
)
