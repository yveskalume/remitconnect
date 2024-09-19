package com.remitconnect.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.key
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.remitconnect.android.navigation.BottomNavDestination
import com.remitconnect.android.navigation.MainNavHost
import com.remitconnect.designsystem.theme.RemitConnectTheme
import com.remitconnect.designsystem.theme.Yellow
import com.remitconnect.designsystem.theme.labelGray

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RemitConnectTheme {
                val navController = rememberNavController()
                val appUiState = rememberAppUiState(navController = navController)
                CompositionLocalProvider(LocalAppUiState provides appUiState) {
                    Box(Modifier.safeDrawingPadding()) {
                        Scaffold(
                            bottomBar = {
                                if (appUiState.isBottomNavVisible) {
                                    NavigationBar {
                                        BottomNavDestination.entries.forEach { destination ->
                                            key(destination.route) {
                                                CustomNavigationBarItem(
                                                    appUiState = appUiState,
                                                    destination = destination
                                                )
                                            }
                                        }

                                    }
                                }
                            }
                        ) { contentPadding ->
                            MainNavHost(
                                navController = navController,
                                modifier = Modifier
                                    .consumeWindowInsets(contentPadding)
                                    .padding(contentPadding)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun RowScope.CustomNavigationBarItem(
    appUiState: AppUiState,
    destination: BottomNavDestination,
    modifier: Modifier = Modifier
) {
    if (destination == BottomNavDestination.SendMoney) {
        Surface(
            onClick = { appUiState.navigateToBottomNavDestination(destination) },
            shape = CircleShape,
            shadowElevation = 1.dp,
            color = Yellow,
            modifier = modifier
        ) {
            Icon(
                painter = painterResource(destination.icon),
                contentDescription = destination.label,
                tint = Color.Unspecified,
                modifier = Modifier
                    .padding(18.dp)
                    .size(24.dp)
            )
        }
    } else {
        NavigationBarItem(
            modifier = modifier,
            colors = NavigationBarItemDefaults.colors(
                indicatorColor = Color.Transparent,
                selectedIconColor = MaterialTheme.colorScheme.primary,
                selectedTextColor = MaterialTheme.colorScheme.primary,
                unselectedIconColor = MaterialTheme.colorScheme.labelGray,
                unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant
            ),
            selected = appUiState.isCurrentDestination(destination.route),
            onClick = { appUiState.navigateToBottomNavDestination(destination) },
            icon = {
                Icon(
                    painter = painterResource(id = destination.icon),
                    contentDescription = destination.label,
                    modifier = Modifier.size(24.dp)
                )
            },
            label = { Text(text = destination.label) }
        )
    }
}