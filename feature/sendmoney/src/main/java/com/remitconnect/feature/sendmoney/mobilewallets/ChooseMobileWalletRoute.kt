package com.remitconnect.feature.sendmoney.mobilewallets

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
object ChooseMobileWalletRoute

fun NavHostController.navigateToChooseMobileWallet(navOptions: NavOptions? = null) {
    navigate(ChooseMobileWalletRoute, navOptions)
}

fun NavGraphBuilder.chooseMobileWallet(
    onNavigateBack: () -> Unit
) {
    composable<ChooseMobileWalletRoute> {
        ChooseMobileWalletScreen(
            onNavigateBack = onNavigateBack
        )
    }
}
