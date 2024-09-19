package com.remitconnect.feature.sendmoney.recipients

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
internal object ChooseRecipientRoute

internal fun NavHostController.navigateToChooseRecipient(navOptions: NavOptions? = null) {
    navigate(ChooseRecipientRoute, navOptions)
}

internal fun NavGraphBuilder.chooseRecipient(
    onNavigateBack: () -> Unit,
    onNavigateToChooseWallet: () -> Unit
) {
    composable<ChooseRecipientRoute> {
        ChooseRecipientScreen(
            onBackClick = onNavigateBack,
            onContinueClick = onNavigateToChooseWallet
        )
    }
}
