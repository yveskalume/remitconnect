package com.remitconnect.feature.sendmoney.sendoptions

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
internal object SendMoneyOptionsRoute

internal fun NavHostController.navigateToSendMoneyOptions(navOptions: NavOptions? = null) {
    navigate(SendMoneyOptionsRoute, navOptions)
}

internal fun NavGraphBuilder.sendMoneyOptions(
    onNavigateBack: () -> Unit,
    onSendToAfricaClick: () -> Unit
) {
    composable<SendMoneyOptionsRoute> {
        SendMoneyOptionsScreen(
            onSendToAfricaClick = onSendToAfricaClick,
            onCloseClick = onNavigateBack,
        )
    }
}