package com.remitconnect.data.dto

import com.remitconnect.domain.model.Country
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CountryDTO(
    @SerialName("id")
    val id: String,
    @SerialName("name")
    val name: String,
    @SerialName("currencyCode")
    val currencyCode: String
)

/**
 * Convert a [CountryDTO] to a [Country]
 */
fun CountryDTO.toDomainModel(): Country {
    return Country(
        id = id,
        name = name,
        currencyCode = currencyCode
    )
}

/**
 * Convert a list of [CountryDTO] to a list of [Country]
 */
fun List<CountryDTO>.toDomainModels(): List<Country> {
    return map { it.toDomainModel() }
}