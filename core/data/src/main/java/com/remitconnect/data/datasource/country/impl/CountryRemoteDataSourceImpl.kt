package com.remitconnect.data.datasource.country.impl

import com.remitconnect.data.datasource.country.CountryRemoteDataSource
import com.remitconnect.data.dto.CountryDTO
import javax.inject.Inject

class CountryRemoteDataSourceImpl @Inject constructor() : CountryRemoteDataSource {
    override suspend fun getCountries(): Result<List<CountryDTO>> {
        TODO("Not yet implemented")
    }
}