package com.remitconnect.feature.sendmoney.mobilewallets

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
internal object ChooseMobileWalletRoute

internal fun NavHostController.navigateToChooseMobileWallet(navOptions: NavOptions? = null) {
    navigate(ChooseMobileWalletRoute, navOptions)
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
