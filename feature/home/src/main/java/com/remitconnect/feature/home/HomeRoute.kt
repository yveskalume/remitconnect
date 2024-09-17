package com.remitconnect.feature.home

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
object HomeRoute

fun NavGraphBuilder.homeRoute(
    onNavigateToSendMoney: () -> Unit,
) {
    composable<HomeRoute> {
        HomeScreen()
    }
}