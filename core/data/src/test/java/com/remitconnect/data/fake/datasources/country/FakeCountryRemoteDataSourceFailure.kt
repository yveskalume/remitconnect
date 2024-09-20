package com.remitconnect.data.fake.datasources.country

import com.remitconnect.data.datasource.country.CountryRemoteDataSource
import com.remitconnect.data.dto.CountryDTO

internal class FakeCountryRemoteDataSourceFailure(
    private val errorMessage: String
) : CountryRemoteDataSource {
    override suspend fun getCountries(): Result<List<CountryDTO>> {
        return Result.failure(Exception(errorMessage))
    }
}
