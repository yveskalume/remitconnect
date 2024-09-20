package com.remitconnect.data.repository

import com.remitconnect.data.datasource.country.CountryRemoteDataSource
import com.remitconnect.data.di.IoDispatcher
import com.remitconnect.data.dto.toDomainModels
import com.remitconnect.domain.model.Country
import com.remitconnect.domain.repository.CountryRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class CountryRepositoryImpl @Inject constructor(
    @IoDispatcher private val coroutineDispatcher: CoroutineDispatcher,
    private val remoteDataSource: CountryRemoteDataSource
) : CountryRepository {

    override suspend fun getCountries(): Result<List<Country>> {
        return withContext(coroutineDispatcher) {
            remoteDataSource.getCountries().map { it.toDomainModels() }
        }
    }
}