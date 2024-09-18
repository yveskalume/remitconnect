package com.remitconnect.designsystem.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowRight
import androidx.compose.material.icons.outlined.AccountBox
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.remitconnect.designsystem.resources.Drawable
import com.remitconnect.designsystem.theme.RemitConnectTheme
import com.remitconnect.designsystem.theme.iconBackground

@Composable
fun MenuItemView(
    icon: @Composable () -> Unit,
    title: @Composable () -> Unit,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .clickable(onClick = onClick)
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Surface(
            color = MaterialTheme.colorScheme.iconBackground,
            shape = MaterialTheme.shapes.small,
            modifier = Modifier.size(40.dp),
            contentColor = MaterialTheme.colorScheme.primary
        ) {
            Box(contentAlignment = Alignment.Center, modifier = Modifier.clearAndSetSemantics { }) {
                icon()
            }
        }
        Spacer(Modifier.size(16.dp))
        CompositionLocalProvider(
            LocalTextStyle provides MaterialTheme.typography.titleSmall
        ) {
            title()
        }
        Spacer(Modifier.weight(1f))
        Icon(
            imageVector = Icons.AutoMirrored.Rounded.KeyboardArrowRight,
            contentDescription = null,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun MenuItemPreview() {
    RemitConnectTheme {
        MenuItemView(
            onClick = {},
            icon = {
                Icon(
                    painter = painterResource(Drawable.userSquare),
                    contentDescription = null,
                )
            },
            title = {
                Text(text = "Bank Transfer")
            }
        )

    }
}