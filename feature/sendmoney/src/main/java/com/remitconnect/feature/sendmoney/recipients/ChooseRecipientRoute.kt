package com.remitconnect.feature.sendmoney.recipients

import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
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
    onNavigateToChooseWallet: (String) -> Unit
) {
    composable<ChooseRecipientRoute> {
        val viewModel = hiltViewModel<ChooseRecipientViewModel>()
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()
        val searchText by viewModel.searchText.collectAsStateWithLifecycle()
        ChooseRecipientScreen(
            searchText = searchText,
            uiState = uiState,
            onAction = { action ->
                when (action) {
                    ChooseRecipientAction.BackActionClick -> onNavigateBack()
                    is ChooseRecipientAction.SelectRecipient -> {
                        onNavigateToChooseWallet(action.recipient.id)
                    }
                    else -> viewModel.handleAction(action)
                }
            }
        )
    }
}
