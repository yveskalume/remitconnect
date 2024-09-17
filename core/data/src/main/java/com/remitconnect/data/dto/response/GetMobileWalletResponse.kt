package com.remitconnect.data.dto.response

import com.remitconnect.data.dto.MobileWalletDTO
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class GetMobileWalletResponse(
    @SerialName("wallets")
    val wallets: List<MobileWalletDTO>
)
