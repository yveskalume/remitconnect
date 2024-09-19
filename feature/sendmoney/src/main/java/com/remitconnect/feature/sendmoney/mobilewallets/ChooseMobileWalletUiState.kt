package com.remitconnect.feature.sendmoney.mobilewallets

import com.remitconnect.domain.model.MobileWallet

internal sealed interface ChooseMobileWalletUiState {
    data object Loading : ChooseMobileWalletUiState
    data class Success(val mobileWallets: List<MobileWallet>,val recipientId: String) : ChooseMobileWalletUiState
    data class Error(val message: String) : ChooseMobileWalletUiState
}