package com.remitconnect.data.datasource.recipient.impl

import com.remitconnect.data.datasource.recipient.RecipientRemoteDataSource
import com.remitconnect.data.dto.RecipientDTO
import javax.inject.Inject

class RecipientRemoteDataSourceImpl @Inject constructor() : RecipientRemoteDataSource {
    override suspend fun getRecipients(): Result<List<RecipientDTO>> {
        TODO("Not yet implemented")
    }
}