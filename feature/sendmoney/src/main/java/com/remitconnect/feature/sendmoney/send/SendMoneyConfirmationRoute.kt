package com.remitconnect.feature.sendmoney.send

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
internal data class SendMoneyConfirmationRoute(val recipientId: String,val mobileWalletId: String)

internal fun NavHostController.navigateToSendMoneyConfirmation(recipientId: String, mobileWalletId: String) {
    navigate(SendMoneyConfirmationRoute(recipientId,mobileWalletId))
}

internal fun NavGraphBuilder.sendMoneyConfirmation(
    onBackClick: () -> Unit,
    onContinueClick: () -> Unit
) {
    composable<SendMoneyConfirmationRoute> {
        SendMoneyConfirmationScreen(
            onBackClick = onBackClick,
            onContinueClick = onContinueClick
        )
    }
}