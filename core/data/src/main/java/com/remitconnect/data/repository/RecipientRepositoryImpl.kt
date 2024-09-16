package com.remitconnect.data.repository

import com.remitconnect.data.datasource.recipient.RecipientRemoteDataSource
import com.remitconnect.data.dto.toDomainModels
import com.remitconnect.domain.model.Recipient
import com.remitconnect.domain.repository.RecipientRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RecipientRepositoryImpl @Inject constructor(
    private val remoteDataSource: RecipientRemoteDataSource
) : RecipientRepository {

    override suspend fun getRecipients(): Result<List<Recipient>> {
        return withContext(Dispatchers.IO) {
            remoteDataSource.getRecipients().map { it.toDomainModels() }
        }
    }
}