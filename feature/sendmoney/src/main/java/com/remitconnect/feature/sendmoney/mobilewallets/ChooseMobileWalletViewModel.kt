package com.remitconnect.feature.sendmoney.mobilewallets

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.remitconnect.domain.repository.MobileWalletRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
internal class ChooseMobileWalletViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val mobileWalletRepository: MobileWalletRepository
) : ViewModel() {

    private val route = savedStateHandle.toRoute<ChooseMobileWalletRoute>()

    val uiState: StateFlow<ChooseMobileWalletUiState> = flow {
        mobileWalletRepository.getMobileWallets().fold(
            onSuccess = {
                emit(
                    ChooseMobileWalletUiState.Success(
                        mobileWallets = it,
                        recipientId = route.recipientId
                    )
                )
            },
            onFailure = {
                emit(ChooseMobileWalletUiState.Error(it.message ?: "Something went wrong"))
            }
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = ChooseMobileWalletUiState.Loading
    )
}