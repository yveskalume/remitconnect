package com.remitconnect.feature.sendmoney.success

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
object SendSuccessRoute

internal fun NavHostController.navigateToSendSuccess(navOptions: NavOptions? = null) {
    navigate(SendSuccessRoute, navOptions)
}


internal fun NavGraphBuilder.sendSuccess(
    onNavigateToHome: () -> Unit
) {
    composable<SendSuccessRoute> {
        SendSuccessScreen(
            onOkClick = onNavigateToHome
        )
    }
}