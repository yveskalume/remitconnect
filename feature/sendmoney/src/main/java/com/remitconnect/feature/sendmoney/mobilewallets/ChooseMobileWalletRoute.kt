package com.remitconnect.feature.sendmoney.mobilewallets

import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
internal data class ChooseMobileWalletRoute(val recipientId: String)

internal fun NavHostController.navigateToChooseMobileWallet(
    recipientId: String,
    navOptions: NavOptions? = null
) {
    navigate(ChooseMobileWalletRoute(recipientId), navOptions)
}

internal fun NavGraphBuilder.chooseMobileWallet(
    onNavigateBack: () -> Unit,
    onNavigateToSendMoneyConfirmation: (recipientId: String, mobileWalletId: String) -> Unit
) {
    composable<ChooseMobileWalletRoute> {
        val viewModel = hiltViewModel<ChooseMobileWalletViewModel>()
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()
        ChooseMobileWalletScreen(
            uiState = uiState,
            onAction = { action ->
                when (action) {
                    is ChooseMobileWalletAction.Continue -> onNavigateToSendMoneyConfirmation(
                        action.recipientId,
                        action.mobileWalletId
                    )

                    ChooseMobileWalletAction.NavigateBack -> onNavigateBack()
                }
            }
        )
    }
}
