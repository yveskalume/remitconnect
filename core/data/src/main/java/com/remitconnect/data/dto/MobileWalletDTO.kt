package com.remitconnect.data.dto

import com.remitconnect.domain.model.MobileWallet
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class MobileWalletDTO(
    @SerialName("id")
    val id: String,
    @SerialName("name")
    val name: String
)

/**
 * Convert a [MobileWalletDTO] to a [MobileWallet]
 */
internal fun MobileWalletDTO.toDomainModel(): MobileWallet {
    return MobileWallet(
        id = id,
        name = name
    )
}

/**
 * Convert a list of [MobileWalletDTO] to a list of [MobileWallet]
 */
internal fun List<MobileWalletDTO>.toDomainModels(): List<MobileWallet> {
    return map { it.toDomainModel() }
}

/**
 * Convert a [MobileWallet] to a [MobileWalletDTO]
 */
internal fun MobileWallet.toDTO(): MobileWalletDTO {
    return MobileWalletDTO(
        id = id,
        name = name
    )
}

/**
 * Convert a list of [MobileWallet] to a list of [MobileWalletDTO]
 */
internal fun List<MobileWallet>.toDTOs(): List<MobileWalletDTO> {
    return map { it.toDTO() }
}