package com.remitconnect.feature.sendmoney

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.navigation
import com.remitconnect.feature.sendmoney.recipients.chooseRecipient
import com.remitconnect.feature.sendmoney.recipients.navigateToChooseRecipient
import com.remitconnect.feature.sendmoney.sendoptions.SendMoneyOptionsRoute
import com.remitconnect.feature.sendmoney.sendoptions.sendMoneyOptions
import com.remitconnect.feature.sendmoney.sendtoafrica.navigateToSendToAfricaOptions
import com.remitconnect.feature.sendmoney.sendtoafrica.sendToAfricaOptions
import kotlinx.serialization.Serializable

@Serializable
object SendMoneyFeatureRoute

fun NavHostController.navigateToSendMoney(navOptions: NavOptions? = null) {
    navigate(SendMoneyFeatureRoute, navOptions)
}

fun NavGraphBuilder.sendMoney(
    navController: NavHostController,
    onNavigateBack: () -> Unit
) {
    navigation<SendMoneyFeatureRoute>(startDestination = SendMoneyOptionsRoute) {
        sendMoneyOptions(
            onNavigateBack = onNavigateBack,
            onSendToAfricaClick = navController::navigateToSendToAfricaOptions
        )
        sendToAfricaOptions(
            onNavigateBack = onNavigateBack,
            onSendWithMobileWallet = navController::navigateToChooseRecipient
        )
        chooseRecipient(
            onNavigateBack = onNavigateBack,
            onNavigateToChooseWallet = {}
        )
    }
}