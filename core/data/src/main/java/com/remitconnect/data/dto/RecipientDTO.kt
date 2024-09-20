package com.remitconnect.data.dto

import com.remitconnect.domain.model.Recipient
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class RecipientDTO(
    @SerialName("id")
    val id: String,
    @SerialName("name")
    val name: String,
    @SerialName("country")
    val country: String,
    @SerialName("mobile_wallet")
    val mobileWallet: String
)

/**
 * Convert a [RecipientDTO] to a [Recipient]
 */
internal fun RecipientDTO.toDomainModel(): Recipient {
    return Recipient(
        id = id,
        name = name,
        country = country,
        mobileWallet = mobileWallet
    )
}

/**
 * Convert a list of [RecipientDTO] to a list of [Recipient]
 */
internal fun List<RecipientDTO>.toDomainModels(): List<Recipient> {
    return map { it.toDomainModel() }
}

/**
 * Convert a [Recipient] to a [RecipientDTO]
 */
internal fun Recipient.toDTO(): RecipientDTO {
    return RecipientDTO(
        id = id,
        name = name,
        country = country,
        mobileWallet = mobileWallet
    )
}

/**
 * Convert a list of [Recipient] to a list of [RecipientDTO]
 */
internal fun List<Recipient>.toDTOs(): List<RecipientDTO> {
    return map { it.toDTO() }
}