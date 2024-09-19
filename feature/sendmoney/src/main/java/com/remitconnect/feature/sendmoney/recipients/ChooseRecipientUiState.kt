package com.remitconnect.feature.sendmoney.recipients

import com.remitconnect.domain.model.Recipient

sealed interface ChooseRecipientUiState {
    data object Loading : ChooseRecipientUiState
    data class Success(val recipients: List<Recipient>) : ChooseRecipientUiState
    data class Error(val message: String) : ChooseRecipientUiState

}