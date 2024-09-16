package com.remitconnect.data.dto

import com.remitconnect.domain.model.MobileWallet
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MobileWalletDTO(
    @SerialName("id")
    val id: String,
    @SerialName("name")
    val name: String
)

/**
 * Convert a [MobileWalletDTO] to a [MobileWallet]
 */
fun MobileWalletDTO.toDomainModel(): MobileWallet {
    return MobileWallet(
        id = id,
        name = name
    )
}

/**
 * Convert a list of [MobileWalletDTO] to a list of [MobileWallet]
 */
fun List<MobileWalletDTO>.toDomainModels(): List<MobileWallet> {
    return map { it.toDomainModel() }
}