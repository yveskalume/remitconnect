package com.remitconnect.feature.sendmoney.recipients

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.remitconnect.domain.model.Recipient
import com.remitconnect.domain.repository.RecipientRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
internal class ChooseRecipientViewModel @Inject constructor(
    private val recipientRepository: RecipientRepository
) : ViewModel() {

    private val _searchText: MutableStateFlow<String> = MutableStateFlow("")
    val searchText: StateFlow<String> = _searchText.asStateFlow()

    val uiState: StateFlow<ChooseRecipientUiState> = searchText.map { text ->
        recipientRepository.getRecipients().fold(
            onSuccess = { recipients ->
                recipients.filterRecipientsSearchText(text)
            },
            onFailure = {
                ChooseRecipientUiState.Error(it.message ?: "Unknown error")
            }
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = ChooseRecipientUiState.Loading
    )


    fun handleAction(action: ChooseRecipientAction) {
        when (action) {
            is ChooseRecipientAction.SearchTextChange -> {
                _searchText.value = action.text
            }

            is ChooseRecipientAction.CreateNewRecipient -> {
                // TODO
            }

            else -> Unit
        }
    }


    private fun List<Recipient>.filterRecipientsSearchText(text: String): ChooseRecipientUiState.Success {
        return if (text.isNotBlank()) {
            ChooseRecipientUiState.Success(
                this.filter { it.name.contains(text, ignoreCase = true) },
            )
        } else {
            ChooseRecipientUiState.Success(this)
        }
    }

}