package com.remitconnect.data.fake.datasources.recipient

import com.remitconnect.data.datasource.recipient.RecipientRemoteDataSource
import com.remitconnect.data.dto.RecipientDTO

internal class FakeRecipientRemoteDataSourceFailure(
    private val errorMessage: String
) : RecipientRemoteDataSource {
    override suspend fun getRecipients(): Result<List<RecipientDTO>> {
        return Result.failure(Exception(errorMessage))
    }
}