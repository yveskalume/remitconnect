package com.remitconnect.data.dto.response

import com.remitconnect.data.dto.RecipientDTO
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class GetRecipientsResponse(
    @SerialName("recipients")
    val recipients: List<RecipientDTO>
)
