package com.remitconnect.feature.sendmoney.mobilewallets

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import com.remitconnect.designsystem.resources.Drawable

@Composable
fun getWalletLogoFromId(walletId: String): Painter? {
    val image =  when(walletId) {
        "1" -> Drawable.cashPlusLogo
        "2" -> Drawable.orangeLogo
        "3" -> Drawable.waveLogo
        "4" -> Drawable.moovLogo
        else -> null
    }

    return image?.let { painterResource(it) }
}