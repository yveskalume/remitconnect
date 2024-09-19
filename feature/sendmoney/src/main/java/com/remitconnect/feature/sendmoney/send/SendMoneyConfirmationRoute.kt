package com.remitconnect.feature.sendmoney.send

import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
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
        val viewModel = hiltViewModel<SendMoneyConfirmationViewModel>()
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()
        val amountText by viewModel.amountText.collectAsStateWithLifecycle()
        val isProcessing by viewModel.isProcessing.collectAsStateWithLifecycle()
        SendMoneyConfirmationScreen(
            uiState = uiState,
            amountText = amountText,
            isProcessing = isProcessing,
            onAction = {action ->
                when(action) {
                    SendMoneyConfirmationAction.Confirm -> onContinueClick()
                    SendMoneyConfirmationAction.NavigateBack -> onBackClick()
                    else -> viewModel.handleAction(action)
                }
            }
        )
    }
}