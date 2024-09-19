package com.remitconnect.feature.sendmoney.mobilewallets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.remitconnect.designsystem.resources.Drawable
import com.remitconnect.designsystem.theme.surfaceGray
import com.remitconnect.designsystem.views.MobileWalletItemView

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChooseMobileWalletScreen(
    onNavigateBack: () -> Unit,
    onNavigateToSendMoneyConfirmation: () -> Unit
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
                        onClick = onNavigateBack,
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
                },

                )
        },
        bottomBar = {
            Surface(
                color = MaterialTheme.colorScheme.background,
                shadowElevation = 8.dp,
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(
                    onClick = onNavigateToSendMoneyConfirmation,
                    modifier = Modifier
                        .padding(bottom = 32.dp, top = 16.dp)
                        .fillMaxWidth()
                        .height(56.dp)
                        .padding(horizontal = 32.dp),
                    shape = MaterialTheme.shapes.medium,
                ) {
                    Text(
                        text = "Continue",
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    ) { contentPadding ->
        val lazyListState = rememberLazyListState()
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .consumeWindowInsets(contentPadding)
                .padding(contentPadding),
            state = lazyListState,
            contentPadding = PaddingValues(top = 8.dp, bottom = 24.dp, start = 24.dp, end = 24.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            item("title") {
                Text(
                    text = "Choose a mobile wallet",
                    style = MaterialTheme.typography.headlineMedium,
                )
            }
            items(4) {
                MobileWalletItemView()
            }
        }
    }
}