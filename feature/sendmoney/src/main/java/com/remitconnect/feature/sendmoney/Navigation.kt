package com.remitconnect.feature.sendmoney

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.navOptions
import androidx.navigation.navigation
import com.remitconnect.feature.sendmoney.mobilewallets.chooseMobileWallet
import com.remitconnect.feature.sendmoney.mobilewallets.navigateToChooseMobileWallet
import com.remitconnect.feature.sendmoney.recipients.chooseRecipient
import com.remitconnect.feature.sendmoney.recipients.navigateToChooseRecipient
import com.remitconnect.feature.sendmoney.send.navigateToSendMoneyConfirmation
import com.remitconnect.feature.sendmoney.send.sendMoneyConfirmation
import com.remitconnect.feature.sendmoney.sendoptions.SendMoneyOptionsRoute
import com.remitconnect.feature.sendmoney.sendoptions.sendMoneyOptions
import com.remitconnect.feature.sendmoney.sendtoafrica.navigateToSendToAfricaOptions
import com.remitconnect.feature.sendmoney.sendtoafrica.sendToAfricaOptions
import com.remitconnect.feature.sendmoney.success.navigateToSendSuccess
import com.remitconnect.feature.sendmoney.success.sendSuccess
import kotlinx.serialization.Serializable

@Serializable
object SendMoneyFeatureRoute

fun NavHostController.navigateToSendMoney(navOptions: NavOptions? = null) {
    navigate(SendMoneyFeatureRoute, navOptions)
}

fun NavGraphBuilder.sendMoney(
    navController: NavHostController,
    onNavigateBack: () -> Unit,
    onNavigateToHome: () -> Unit
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
            onNavigateToChooseWallet = navController::navigateToChooseMobileWallet
        )
        chooseMobileWallet(
            onNavigateBack = onNavigateBack,
            onNavigateToSendMoneyConfirmation = navController::navigateToSendMoneyConfirmation
        )
        sendMoneyConfirmation(
            onBackClick = onNavigateBack,
            onContinueClick = {
                val navOptions = navOptions {
                    navController.graph.startDestinationRoute?.let {
                        popUpTo(it)
                    }
                }
                navController.navigateToSendSuccess(navOptions)
            }
        )
        sendSuccess(
            onNavigateToHome = {
                onNavigateToHome()
                navController.popBackStack()
            }
        )
    }
}