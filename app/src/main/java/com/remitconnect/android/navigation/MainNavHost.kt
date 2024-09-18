package com.remitconnect.android.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.remitconnect.feature.home.HomeRoute
import com.remitconnect.feature.home.homeRoute
import com.remitconnect.feature.sendmoney.sendMoney

@Composable
fun MainNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(navController = navController, startDestination = HomeRoute, modifier = modifier) {

        homeRoute()

        sendMoney(
            navController = navController,
            onNavigateBack = navController::navigateUp
        )
    }
}