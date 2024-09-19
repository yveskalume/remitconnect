package com.remitconnect.android

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.Stable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.remitconnect.android.navigation.BottomNavDestination
import com.remitconnect.feature.home.navigateToHome
import com.remitconnect.feature.sendmoney.navigateToSendMoney

@Composable
fun rememberAppUiState(
    navController: NavHostController,
) = remember {
    AppUiState(navController = navController)
}

@Stable
class AppUiState(
    private val navController: NavHostController
) {

    private val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    val isBottomNavVisible: Boolean
        @Composable
        get() {
            return currentDestination?.route == BottomNavDestination.Home.route
        }


    @Composable
    fun isCurrentDestination(route: String): Boolean {
        return currentDestination?.route == route
    }


    fun navigateToBottomNavDestination(destination: BottomNavDestination) {
        when (destination) {
            BottomNavDestination.Home -> navController.navigateToHome()
            BottomNavDestination.SendMoney -> navController.navigateToSendMoney()

            BottomNavDestination.Card -> {
                //TODO()
            }

            BottomNavDestination.Tontines -> {
                //TODO()
            }

            BottomNavDestination.Settings -> {
                //TODO()
            }
        }
    }
}

val LocalAppUiState: ProvidableCompositionLocal<AppUiState> =
    compositionLocalOf { error("CompositionLocal LocalAppUiState not present") }