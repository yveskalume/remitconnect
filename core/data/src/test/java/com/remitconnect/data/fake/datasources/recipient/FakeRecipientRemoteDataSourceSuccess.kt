package com.remitconnect.data.fake.datasources.recipient

import com.remitconnect.data.datasource.recipient.RecipientRemoteDataSource
import com.remitconnect.data.dto.RecipientDTO
import com.remitconnect.data.dto.toDTOs
import com.remitconnect.domain.model.Recipient

internal class FakeRecipientRemoteDataSourceSuccess(
    private val fakeRecipients: List<Recipient>
) : RecipientRemoteDataSource {
    override suspend fun getRecipients(): Result<List<RecipientDTO>> {
        return Result.success(fakeRecipients.toDTOs())
    }
}
