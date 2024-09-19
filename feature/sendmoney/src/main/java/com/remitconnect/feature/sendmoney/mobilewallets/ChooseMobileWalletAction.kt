package com.remitconnect.feature.sendmoney.mobilewallets

sealed interface ChooseMobileWalletAction {
    data class Continue(val recipientId: String,val mobileWalletId: String) : ChooseMobileWalletAction
    data object NavigateBack : ChooseMobileWalletAction
}