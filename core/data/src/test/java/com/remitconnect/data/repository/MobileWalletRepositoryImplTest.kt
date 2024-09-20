package com.remitconnect.data.repository

import com.remitconnect.data.fake.datasources.mobilewallet.FakeMobileWalletRemoteDataSourceFailure
import com.remitconnect.data.fake.datasources.mobilewallet.FakeMobileWalletRemoteDataSourceSuccess
import com.remitconnect.domain.model.MobileWallet
import com.remitconnect.testing.rules.MainDispatcherRule
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class MobileWalletRepositoryImplTest {
    @get:Rule
    val dispatcherRule = MainDispatcherRule()

    private lateinit var mobileWalletRepository: MobileWalletRepositoryImpl

    @Test
    fun `should return mobile wallets successfully when remote data source returns success`() = runTest {
        val fakeMobileWallets = listOf(
            MobileWallet(id = "1", name = "Wave"),
            MobileWallet(id = "2", name = "Orange Money")
        )

        val fakeRemoteDataSource = FakeMobileWalletRemoteDataSourceSuccess(fakeMobileWallets)

        mobileWalletRepository = MobileWalletRepositoryImpl(UnconfinedTestDispatcher(), fakeRemoteDataSource)

        val result = mobileWalletRepository.getMobileWallets()

        assertTrue(result.isSuccess)
        assertEquals(fakeMobileWallets, result.getOrNull())
    }

    @Test
    fun `should return failure when remote data source returns failure`() = runTest {

        val errorMessage = "Failed to fetch mobile wallets"
        val fakeRemoteDataSource = FakeMobileWalletRemoteDataSourceFailure(errorMessage)

        mobileWalletRepository = MobileWalletRepositoryImpl(UnconfinedTestDispatcher(), fakeRemoteDataSource)

        val result = mobileWalletRepository.getMobileWallets()

        assertTrue(result.isFailure)
        assertEquals(errorMessage, result.exceptionOrNull()?.message)
    }


}