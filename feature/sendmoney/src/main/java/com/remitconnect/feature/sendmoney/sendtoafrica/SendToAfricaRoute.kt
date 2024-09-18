package com.remitconnect.feature.sendmoney.sendtoafrica

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
internal object SendToAfricaOptionsRoute

internal fun NavHostController.navigateToSendToAfricaOptions(navOptions: NavOptions? = null) {
    navigate(SendToAfricaOptionsRoute, navOptions)
}

internal fun NavGraphBuilder.sendToAfricaOptions(
    onNavigateBack: () -> Unit
) {
    composable<SendToAfricaOptionsRoute> {
        SendToAfricaOptionsScreen(
            onBackClick = onNavigateBack
        )
    }
}