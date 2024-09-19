package com.remitconnect.feature.sendmoney.send

import com.remitconnect.domain.model.MobileWallet
import com.remitconnect.domain.model.Recipient
import kotlin.math.round

sealed interface SendMoneyConfirmationUiState {
    data object Loading : SendMoneyConfirmationUiState
    data class Success(
        val amount: Double,
        val monecoFees: Double,
        val transferFees: Double,
        val conversionRate: Double,
        val recipientCurrency: String,
        val canSend: Boolean,
        val recipient: Recipient,
        val mobileWallet: MobileWallet
    ) : SendMoneyConfirmationUiState {
        val totalInActualCurrency: Double
            get() = amount + monecoFees + transferFees

        /**
         * get only two digits after the decimal point
         */
        val amountGotByRecipient: Double
            get() = round(amount * conversionRate * 100) / 100

    }

    data class Error(val message: String) : SendMoneyConfirmationUiState
}