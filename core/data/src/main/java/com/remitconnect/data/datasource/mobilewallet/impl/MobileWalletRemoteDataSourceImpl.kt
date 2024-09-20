package com.remitconnect.data.datasource.mobilewallet.impl

import com.remitconnect.data.datasource.mobilewallet.MobileWalletRemoteDataSource
import com.remitconnect.data.dto.MobileWalletDTO
import com.remitconnect.data.dto.response.GetMobileWalletResponse
import com.remitconnect.data.util.HttpException
import com.remitconnect.data.util.ServerEndpoints
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.isSuccess
import javax.inject.Inject

internal class MobileWalletRemoteDataSourceImpl @Inject constructor(
    private val httpClient: HttpClient,
) : MobileWalletRemoteDataSource {
    override suspend fun getMobileWallets(): Result<List<MobileWalletDTO>> {
        return runCatching {
            val response = httpClient.get(ServerEndpoints.WALLETS)
            if (response.status.isSuccess()) {
                response.body<GetMobileWalletResponse>()
            } else {
                throw HttpException("An error occurred while fetching mobile wallets")
            }
        }
    }
}