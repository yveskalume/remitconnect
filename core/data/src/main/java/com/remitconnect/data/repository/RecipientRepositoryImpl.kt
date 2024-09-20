package com.remitconnect.data.repository

import com.remitconnect.data.datasource.recipient.RecipientRemoteDataSource
import com.remitconnect.data.di.IoDispatcher
import com.remitconnect.data.dto.toDomainModels
import com.remitconnect.domain.model.Recipient
import com.remitconnect.domain.repository.RecipientRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class RecipientRepositoryImpl @Inject constructor(
    @IoDispatcher private val coroutineDispatcher: CoroutineDispatcher,
    private val remoteDataSource: RecipientRemoteDataSource
) : RecipientRepository {

    override suspend fun getRecipients(): Result<List<Recipient>> {
        return withContext(coroutineDispatcher) {
            remoteDataSource.getRecipients().map { it.toDomainModels() }
        }
    }
}