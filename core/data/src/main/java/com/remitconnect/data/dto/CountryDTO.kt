package com.remitconnect.data.dto

import com.remitconnect.domain.model.Country
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class CountryDTO(
    @SerialName("id")
    val id: String,
    @SerialName("name")
    val name: String,
    @SerialName("currency_code")
    val currencyCode: String
)

/**
 * Convert a [CountryDTO] to a [Country]
 */
internal fun CountryDTO.toDomainModel(): Country {
    return Country(
        id = id,
        name = name,
        currencyCode = currencyCode
    )
}

/**
 * Convert a list of [CountryDTO] to a list of [Country]
 */
internal fun List<CountryDTO>.toDomainModels(): List<Country> {
    return map { it.toDomainModel() }
}

/**
 * Convert a [Country] to a [CountryDTO]
 */
internal fun Country.toDTO(): CountryDTO {
    return CountryDTO(
        id = id,
        name = name,
        currencyCode = currencyCode
    )
}

/**
 * Convert a list of [Country] to a list of [CountryDTO]
 */
internal fun List<Country>.toDTOs(): List<CountryDTO> {
    return map { it.toDTO() }
}