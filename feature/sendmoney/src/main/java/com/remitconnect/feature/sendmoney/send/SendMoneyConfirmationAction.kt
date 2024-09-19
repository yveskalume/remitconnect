package com.remitconnect.feature.sendmoney.send

sealed interface SendMoneyConfirmationAction {
    data class AmountChanged(val amount: String) : SendMoneyConfirmationAction
    data object Confirm : SendMoneyConfirmationAction
    data object NavigateBack : SendMoneyConfirmationAction
}