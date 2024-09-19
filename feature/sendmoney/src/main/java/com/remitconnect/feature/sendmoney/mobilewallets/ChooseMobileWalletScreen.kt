package com.remitconnect.feature.sendmoney.mobilewallets

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.remitconnect.designsystem.resources.Drawable
import com.remitconnect.designsystem.theme.surfaceGray
import com.remitconnect.designsystem.views.MobileWalletItemView

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
internal fun ChooseMobileWalletScreen(
    uiState: ChooseMobileWalletUiState,
    onAction: (ChooseMobileWalletAction) -> Unit
) {
    var selectedWalletId: String? by rememberSaveable {
        mutableStateOf(null)
    }

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
                        onClick = {
                            onAction(ChooseMobileWalletAction.NavigateBack)
                        },
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
                    enabled = selectedWalletId != null,
                    onClick = {
                        if (selectedWalletId != null && uiState is ChooseMobileWalletUiState.Success) {
                            onAction(
                                ChooseMobileWalletAction.Continue(
                                    recipientId = uiState.recipientId,
                                    mobileWalletId = selectedWalletId!!
                                )
                            )
                        }
                    },
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
            verticalArrangement = Arrangement.spacedBy(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item("title") {
                Text(
                    text = "Choose a mobile wallet",
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.fillMaxWidth()
                )
            }
            when (uiState) {
                is ChooseMobileWalletUiState.Error -> {
                    item("error-message") {
                        Text(
                            text = uiState.message,
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.padding(24.dp).animateItem()
                        )
                    }
                }

                ChooseMobileWalletUiState.Loading -> {
                    item("loader") {
                        CircularProgressIndicator(modifier = Modifier.animateItem())
                    }
                }

                is ChooseMobileWalletUiState.Success -> {
                    items(uiState.mobileWallets, key = { it.id }) { wallet ->
                        getWalletLogoFromId(wallet.id)?.let {logo ->
                            MobileWalletItemView(
                                logo = logo,
                                name = wallet.name,
                                modifier = Modifier.animateItem(),
                                isSelected = wallet.id == selectedWalletId,
                                onClick = {
                                    selectedWalletId = wallet.id
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}