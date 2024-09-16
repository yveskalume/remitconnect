package com.remitconnect.data.datasource.country

import com.remitconnect.data.dto.CountryDTO

interface CountryRemoteDataSource {
    suspend fun getCountries() : Result<List<CountryDTO>>
}