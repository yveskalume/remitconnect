package com.remitconnect.data.fake.datasources.mobilewallet

import com.remitconnect.data.datasource.mobilewallet.MobileWalletRemoteDataSource
import com.remitconnect.data.dto.MobileWalletDTO
import com.remitconnect.data.dto.toDTOs
import com.remitconnect.domain.model.MobileWallet

internal class FakeMobileWalletRemoteDataSourceSuccess(
    private val fakeMobileWallets: List<MobileWallet>
) : MobileWalletRemoteDataSource {
    override suspend fun getMobileWallets(): Result<List<MobileWalletDTO>> {
        return Result.success(fakeMobileWallets.toDTOs())
    }
}