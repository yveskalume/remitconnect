package com.remitconnect.android.navigation

import androidx.annotation.DrawableRes
import com.remitconnect.designsystem.resources.Drawable
import com.remitconnect.feature.home.HomeRoute

enum class BottomNavDestination(
    @DrawableRes val icon: Int,
    val label: String,
    val route: String
) {
    Home(
        icon = Drawable.homeF,
        label = "Home",
        route = HomeRoute::class.qualifiedName.toString()
    ),
    Card(
        icon = Drawable.creditCardF,
        label = "Card",
        route = ""
    ),
    SendMoney(
        icon = Drawable.paperPlane,
        label = "Send Money",
        route = ""
    ),
    Tontines(
        icon = Drawable.coinF,
        label = "Tontines",
        route = ""
    ),
    Settings(
        icon = Drawable.cogF,
        label = "Settings",
        route = ""
    )
}