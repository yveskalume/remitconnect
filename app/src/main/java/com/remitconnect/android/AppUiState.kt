package com.remitconnect.android

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.Stable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navOptions
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
        val navOptions = navOptions {
            /**
             * Pop up to the start destination of the graph to
             * avoid building up a large stack of destinations
             * on the back stack as users select items
             */
            navController.graph.startDestinationRoute?.let {
                popUpTo(it) {
                    saveState = true
                }
            }
            /**
             * Avoid multiple copies of the same destination when reselecting the same item
             */
            launchSingleTop = true
            /**
             * Restore state when reselecting a previously selected item
             */
            restoreState = true
        }

        when (destination) {
            BottomNavDestination.Home -> navController.navigateToHome(navOptions)
            BottomNavDestination.SendMoney -> navController.navigateToSendMoney(navOptions)

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