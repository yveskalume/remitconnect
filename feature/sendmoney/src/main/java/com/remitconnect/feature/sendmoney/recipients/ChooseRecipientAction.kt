package com.remitconnect.feature.sendmoney.recipients

import com.remitconnect.domain.model.Recipient

sealed interface ChooseRecipientAction {
    data class SearchTextChange(val text: String) : ChooseRecipientAction
    data class SelectRecipient(val recipient: Recipient) : ChooseRecipientAction
    data object BackActionClick : ChooseRecipientAction
    data class CreateNewRecipient(val phoneNumber: String, val firstName: String, val lastName: String) : ChooseRecipientAction
}