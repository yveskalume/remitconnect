package com.remitconnect.data.dto

import com.remitconnect.domain.model.Recipient
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RecipientDTO(
    @SerialName("id")
    val id: String,
    @SerialName("name")
    val name: String,
    @SerialName("country")
    val country: String,
    @SerialName("mobileWallet")
    val mobileWallet: String
)

/**
 * Convert a [RecipientDTO] to a [Recipient]
 */
fun RecipientDTO.toDomainModel(): Recipient {
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
fun List<RecipientDTO>.toDomainModels(): List<Recipient> {
    return map { it.toDomainModel() }
}