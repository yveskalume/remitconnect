package com.remitconnect.data.repository

import com.remitconnect.data.datasource.mobilewallet.MobileWalletRemoteDataSource
import com.remitconnect.data.di.IoDispatcher
import com.remitconnect.data.dto.toDomainModels
import com.remitconnect.domain.model.MobileWallet
import com.remitconnect.domain.repository.MobileWalletRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class MobileWalletRepositoryImpl @Inject constructor(
    @IoDispatcher private val coroutineDispatcher: CoroutineDispatcher,
    private val remoteDataSource: MobileWalletRemoteDataSource
) : MobileWalletRepository {

    override suspend fun getMobileWallets(): Result<List<MobileWallet>> {
        return withContext(coroutineDispatcher) {
            remoteDataSource.getMobileWallets().map { it.toDomainModels() }
        }
    }
}