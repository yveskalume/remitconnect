package com.remitconnect.data.datasource.mobilewallet

import com.remitconnect.data.dto.MobileWalletDTO

interface MobileWalletRemoteDataSource {
    suspend fun getMobileWallets() : Result<List<MobileWalletDTO>>
}