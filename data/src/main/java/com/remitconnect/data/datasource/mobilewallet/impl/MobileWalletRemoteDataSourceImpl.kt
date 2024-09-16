package com.remitconnect.data.datasource.mobilewallet.impl

import com.remitconnect.data.datasource.mobilewallet.MobileWalletRemoteDataSource
import com.remitconnect.data.dto.MobileWalletDTO
import javax.inject.Inject

class MobileWalletRemoteDataSourceImpl @Inject constructor() : MobileWalletRemoteDataSource {
    override suspend fun getMobileWallets(): Result<List<MobileWalletDTO>> {
        TODO("Not yet implemented")
    }
}