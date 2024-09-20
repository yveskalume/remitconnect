package com.remitconnect.data.fake.datasources.country

import com.remitconnect.data.datasource.country.CountryRemoteDataSource
import com.remitconnect.data.dto.CountryDTO
import com.remitconnect.data.dto.toDTOs
import com.remitconnect.domain.model.Country

internal class FakeCountryRemoteDataSourceSuccess(
    private val countries: List<Country>
) : CountryRemoteDataSource {
    override suspend fun getCountries(): Result<List<CountryDTO>> {
        return Result.success(countries.toDTOs())
    }
}
