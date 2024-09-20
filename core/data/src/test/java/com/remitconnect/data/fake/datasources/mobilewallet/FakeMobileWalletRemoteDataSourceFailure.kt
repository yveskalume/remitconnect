package com.remitconnect.data.fake.datasources.mobilewallet

import com.remitconnect.data.datasource.mobilewallet.MobileWalletRemoteDataSource
import com.remitconnect.data.dto.MobileWalletDTO

internal class FakeMobileWalletRemoteDataSourceFailure(
    private val errorMessage: String
) : MobileWalletRemoteDataSource {
    override suspend fun getMobileWallets(): Result<List<MobileWalletDTO>> {
        return Result.failure(Exception(errorMessage))
    }
}