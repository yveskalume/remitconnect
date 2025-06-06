package com.remitconnect.feature.sendmoney.sendtoafrica

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.remitconnect.designsystem.resources.Drawable
import com.remitconnect.designsystem.theme.RemitConnectTheme
import com.remitconnect.designsystem.theme.surfaceGray
import com.remitconnect.designsystem.views.MenuItemView

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun SendToAfricaOptionsScreen(
    onBackClick: () -> Unit,
    onMobileWalletClick: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                ),
                title = { },
                navigationIcon = {
                    Surface(
                        modifier = Modifier.padding(start = 8.dp),
                        onClick = onBackClick,
                        shape = MaterialTheme.shapes.medium,
                        color = MaterialTheme.colorScheme.surfaceGray
                    ) {
                        Icon(
                            painter = painterResource(Drawable.arrowLeft),
                            contentDescription = null,
                            modifier = Modifier
                                .padding(10.dp)
                                .size(20.dp)
                        )
                    }
                }
            )
        }
    ) { contentPadding ->
        val scrollState = rememberScrollState()
        Column(
            modifier = Modifier
                .verticalScroll(scrollState)
                .fillMaxWidth()
                .wrapContentHeight()
                .consumeWindowInsets(contentPadding)
                .padding(contentPadding)
        ) {
            Text(
                text = "Send to Africa",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier
                    .padding(bottom = 24.dp, top = 8.dp)
                    .padding(horizontal = 24.dp)
            )
            HorizontalDivider(thickness = 0.5.dp)
            MenuItemView(
                icon = {
                    Icon(
                        painter = painterResource(Drawable.arrowSquareRightF),
                        contentDescription = null
                    )
                },
                title = {
                    Text(text = "Mobile wallets")
                },
                onClick = onMobileWalletClick,
            )
            HorizontalDivider(thickness = 0.5.dp)
            MenuItemView(
                icon = {
                    Icon(
                        painter = painterResource(Drawable.arrowSquareRightF),
                        contentDescription = null
                    )
                },
                title = {
                    Text(text = "Bank transfer")
                },
                onClick = {},
            )
            HorizontalDivider(thickness = 0.5.dp)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SendToAfricaOptionsScreenPreview() {
    RemitConnectTheme {
        SendToAfricaOptionsScreen(onBackClick = {}, onMobileWalletClick = {})
    }
}