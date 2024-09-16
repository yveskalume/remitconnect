package com.remitconnect.domain.repository

import com.remitconnect.domain.model.Recipient

interface RecipientRepository {
    suspend fun getRecipients(): List<Recipient>
}