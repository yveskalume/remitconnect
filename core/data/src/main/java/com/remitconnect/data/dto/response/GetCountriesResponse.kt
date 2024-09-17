package com.remitconnect.data.dto.response

import com.remitconnect.data.dto.CountryDTO
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class GetCountriesResponse(
    @SerialName("countries")
    val countries: List<CountryDTO>
)
