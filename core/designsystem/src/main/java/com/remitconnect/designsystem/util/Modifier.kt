package com.remitconnect.designsystem.util

import androidx.compose.ui.Modifier

fun Modifier.block(block: Modifier.() -> Modifier): Modifier {
    return this then Modifier.block()
}