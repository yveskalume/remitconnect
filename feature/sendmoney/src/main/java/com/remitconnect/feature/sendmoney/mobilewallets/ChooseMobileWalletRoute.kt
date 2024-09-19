package com.remitconnect.feature.sendmoney.mobilewallets

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
    onNavigateToSendMoneyConfirmation: () -> Unit
) {
    composable<ChooseMobileWalletRoute> {
        ChooseMobileWalletScreen(
            onNavigateBack = onNavigateBack,
            onNavigateToSendMoneyConfirmation = onNavigateToSendMoneyConfirmation
        )
    }
}
