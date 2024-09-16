package com.remitconnect.domain.repository

import com.remitconnect.domain.model.MobileWallet

interface MobileWalletRepository {
    suspend fun getMobileWallets(): Result<List<MobileWallet>>
}