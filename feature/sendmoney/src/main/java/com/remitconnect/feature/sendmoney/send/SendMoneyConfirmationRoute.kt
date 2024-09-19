package com.remitconnect.feature.sendmoney.send

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
object SendMoneyConfirmationRoute

fun NavHostController.navigateToSendMoneyConfirmation() {
    navigate(SendMoneyConfirmationRoute)
}

fun NavGraphBuilder.sendMoneyConfirmationScreen(
    onBackClick: () -> Unit
) {
    composable<SendMoneyConfirmationRoute> {
        SendMoneyConfirmationScreen(
            onBackClick = onBackClick
        )
    }
}