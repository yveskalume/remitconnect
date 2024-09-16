package com.remitconnect.domain.repository

import com.remitconnect.domain.model.Country

interface CountryRepository {
    suspend fun getCountries(): List<Country>
}