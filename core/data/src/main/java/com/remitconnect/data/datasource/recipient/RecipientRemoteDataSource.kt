package com.remitconnect.data.datasource.recipient

import com.remitconnect.data.dto.RecipientDTO

interface RecipientRemoteDataSource {
    suspend fun getRecipients() : Result<List<RecipientDTO>>
}