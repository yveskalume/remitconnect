package com.remitconnect.data.repository

import com.remitconnect.data.fake.datasources.country.FakeCountryRemoteDataSourceFailure
import com.remitconnect.data.fake.datasources.country.FakeCountryRemoteDataSourceSuccess
import com.remitconnect.domain.model.Country
import com.remitconnect.testing.rules.MainDispatcherRule
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class CountryRepositoryImplTest {
    @get:Rule
    val dispatcherRule = MainDispatcherRule()

    private lateinit var countryRepository: CountryRepositoryImpl



    @Test
    fun `should return countries successfully when remote data source returns success`() = runTest {
        // Given
        val fakeCountries = listOf(
            Country(id = "1", name = "Ghana", currencyCode = "GHS"),
            Country(id = "2", name = "Nigeria", currencyCode = "NGN")
        )

        val fakeRemoteDataSource = FakeCountryRemoteDataSourceSuccess(fakeCountries)

        countryRepository = CountryRepositoryImpl(UnconfinedTestDispatcher(), fakeRemoteDataSource)


        val result = countryRepository.getCountries()

        assertTrue(result.isSuccess)
        assertEquals(fakeCountries, result.getOrNull())
    }

    @Test
    fun `should return failure when remote data source returns failure`() = runTest {
        val errorMessage = "Network error"

        val fakeRemoteDataSource = FakeCountryRemoteDataSourceFailure(errorMessage)

        countryRepository = CountryRepositoryImpl(UnconfinedTestDispatcher(), fakeRemoteDataSource)

        val result = countryRepository.getCountries()

        assertTrue(result.isFailure)
        assertEquals(errorMessage, result.exceptionOrNull()?.message)
    }

}