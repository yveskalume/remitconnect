package com.remitconnect.data.repository

import com.remitconnect.data.datasource.country.CountryRemoteDataSource
import com.remitconnect.data.dto.toDomainModels
import com.remitconnect.domain.model.Country
import com.remitconnect.domain.repository.CountryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CountryRepositoryImpl @Inject constructor(
    private val remoteDataSource: CountryRemoteDataSource
) : CountryRepository {

    override suspend fun getCountries(): Result<List<Country>> {
        return withContext(Dispatchers.IO) {
            remoteDataSource.getCountries().map { it.toDomainModels() }
        }
    }
}