package com.remitconnect.data.datasource.country.impl

import com.remitconnect.data.datasource.country.CountryRemoteDataSource
import com.remitconnect.data.dto.CountryDTO
import com.remitconnect.data.dto.response.GetCountriesResponse
import com.remitconnect.data.util.HttpException
import com.remitconnect.data.util.ServerEndpoints
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.isSuccess
import javax.inject.Inject

internal class CountryRemoteDataSourceImpl @Inject constructor(
    private val httpClient: HttpClient
) : CountryRemoteDataSource {
    override suspend fun getCountries(): Result<List<CountryDTO>> {
        return runCatching {
            val response = httpClient.get(ServerEndpoints.COUNTRIES)
            if (response.status.isSuccess()) {
                response.body<GetCountriesResponse>()
            } else {
                throw HttpException("An error occurred while fetching countries")
            }
        }
    }
}