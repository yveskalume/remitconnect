package com.remitconnect.data.repository

import com.remitconnect.data.datasource.mobilewallet.MobileWalletRemoteDataSource
import com.remitconnect.data.dto.toDomainModels
import com.remitconnect.domain.model.MobileWallet
import com.remitconnect.domain.repository.MobileWalletRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class MobileWalletRepositoryImpl @Inject constructor(
    private val remoteDataSource: MobileWalletRemoteDataSource
) : MobileWalletRepository {

    override suspend fun getMobileWallets(): Result<List<MobileWallet>> {
        return withContext(Dispatchers.IO) {
            remoteDataSource.getMobileWallets().map { it.toDomainModels() }
        }
    }
}