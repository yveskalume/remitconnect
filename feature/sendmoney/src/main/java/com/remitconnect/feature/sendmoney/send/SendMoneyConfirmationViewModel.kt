package com.remitconnect.feature.sendmoney.send

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.remitconnect.domain.model.Country
import com.remitconnect.domain.model.MobileWallet
import com.remitconnect.domain.model.Recipient
import com.remitconnect.domain.repository.CountryRepository
import com.remitconnect.domain.repository.MobileWalletRepository
import com.remitconnect.domain.repository.RecipientRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class SendMoneyConfirmationViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val recipientRepository: RecipientRepository,
    private val countryRepository: CountryRepository,
    private val mobileWalletRepository: MobileWalletRepository
) : ViewModel() {

    private val route = savedStateHandle.toRoute<SendMoneyConfirmationRoute>()

    private val _isProcessing = MutableStateFlow(false)
    val isProcessing: StateFlow<Boolean> = _isProcessing.asStateFlow()

    private val _amountText: MutableStateFlow<String> = MutableStateFlow("")
    val amountText: StateFlow<String> = _amountText.asStateFlow()

    @OptIn(ExperimentalCoroutinesApi::class)
    val uiState: StateFlow<SendMoneyConfirmationUiState> = _amountText
        .onEach { _isProcessing.value = true }
        .flatMapLatest { amount ->
            flow {
                val mobileWalletResultDeferred =  viewModelScope.async {
                    getMobileWalletById(route.mobileWalletId)
                }

                val recipientResult = getRecipientById(route.recipientId)
                val recipient = recipientResult.getOrElse {
                    emit(SendMoneyConfirmationUiState.Error(it.message ?: "Recipient not found"))
                    return@flow
                }


                val mobileWallet = mobileWalletResultDeferred.await().getOrElse {
                    emit(
                        SendMoneyConfirmationUiState.Error(
                            it.message ?: "Mobile wallet not found"
                        )
                    )
                    return@flow
                }

                val countryResult = getCountryByName(recipient.country)
                val country = countryResult.getOrElse {
                    emit(SendMoneyConfirmationUiState.Error(it.message ?: "Country not found"))
                    return@flow
                }

                val formatedAmount = amount.toDoubleOrNull() ?: 0.0
                emit(
                    SendMoneyConfirmationUiState.Success(
                        amount = formatedAmount,
                        recipientCurrency = country.currencyCode,
                        canSend = (amount.toDoubleOrNull() ?: 0.0) > 0,
                        recipient = recipient,
                        mobileWallet = mobileWallet,
                        monecoFees = 0.0,
                        transferFees = 0.0,
                        conversionRate = 655.94,
                    )
                )
            }
        }
        .onEach {
            _isProcessing.value = false
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = SendMoneyConfirmationUiState.Loading
        )


    fun handleAction(action: SendMoneyConfirmationAction) {
        when (action) {
            is SendMoneyConfirmationAction.AmountChanged -> validateAndUpdateAmount(action.amount)
            else -> Unit
        }
    }

    private fun validateAndUpdateAmount(newAmount: String) {
        if (newAmount.toDoubleOrNull() != null || newAmount.isEmpty()) {
            _amountText.value = newAmount
        }
    }

    private suspend fun getRecipientById(recipientId: String): Result<Recipient> {
        return recipientRepository.getRecipients().mapCatching { recipients ->
            recipients.first { it.id == recipientId }
        }
    }

    private suspend fun getCountryByName(countryName: String): Result<Country> {
        return countryRepository.getCountries().mapCatching { countries ->
            countries.first { it.name == countryName }
        }
    }

    private suspend fun getMobileWalletById(mobileWalletId: String): Result<MobileWallet> {
        return mobileWalletRepository.getMobileWallets().mapCatching { mobileWallets ->
            mobileWallets.first { it.id == mobileWalletId }
        }
    }
}