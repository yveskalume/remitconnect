package com.remitconnect.data.datasource.recipient.impl

import com.remitconnect.data.datasource.recipient.RecipientRemoteDataSource
import com.remitconnect.data.dto.RecipientDTO
import com.remitconnect.data.dto.response.GetRecipientsResponse
import com.remitconnect.data.util.HttpException
import com.remitconnect.data.util.ServerEndpoints
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.isSuccess
import javax.inject.Inject

internal class RecipientRemoteDataSourceImpl @Inject constructor(
    private val httpClient: HttpClient,
) : RecipientRemoteDataSource {
    override suspend fun getRecipients(): Result<List<RecipientDTO>> {
        return runCatching {
            val response = httpClient.get(ServerEndpoints.RECIPIENTS)
            if (response.status.isSuccess()) {
                response.body<GetRecipientsResponse>()
            } else {
                throw HttpException("An error occurred while fetching recipients")
            }
        }.onSuccess {

        }
    }
}