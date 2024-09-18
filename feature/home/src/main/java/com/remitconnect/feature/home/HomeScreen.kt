package com.remitconnect.feature.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.runtime.key
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.remitconnect.designsystem.resources.Drawable
import com.remitconnect.designsystem.theme.BackgroundColor
import com.remitconnect.designsystem.theme.Green200
import com.remitconnect.designsystem.theme.Green400
import com.remitconnect.designsystem.theme.RemitConnectTheme
import com.remitconnect.designsystem.theme.surfaceGray
import com.remitconnect.designsystem.views.DashboardCardView
import com.remitconnect.designsystem.views.TransactionView

@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    val scrollState = rememberScrollState()
    Scaffold(
        containerColor = BackgroundColor,
        topBar = {
            TopAppBar(
                title = {},
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = BackgroundColor,
                )
            )
        }
    ) {contentPadding ->
        Column(
            modifier = Modifier
                .verticalScroll(scrollState)
                .wrapContentHeight()
                .fillMaxWidth()
                .consumeWindowInsets(contentPadding)
                .padding(contentPadding)
                .padding(horizontal = 24.dp)
        ) {
            Header(
                onNotificationClick = {},
                modifier = Modifier.fillMaxWidth()
            )

            UserInfoSection(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 26.dp)
            )

            FlowRow(
                modifier = Modifier.padding(top = 24.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                maxItemsInEachRow = 2,
            ) {
                DashboardCardView(
                    icon = Drawable.emptyWalletAdd,
                    title = "Top up balance",
                    onClick = {},
                    modifier = Modifier.weight(1f)
                )

                DashboardCardView(
                    icon = Drawable.walletMinus,
                    title = "Withdraw money",
                    onClick = {},
                    modifier = Modifier.weight(1f)
                )

                DashboardCardView(
                    icon = Drawable.walletMinus,
                    title = "Withdraw money",
                    onClick = {},
                    modifier = Modifier.weight(1f)
                )
                DashboardCardView(
                    icon = Drawable.walletMinus,
                    title = "Withdraw money",
                    onClick = {},
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(40.dp))

            Text(
                text = "Transactions",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(bottom = 16.dp),
            )

            Card(
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceContainer
                )
            ) {
                (1..10).forEach {
                    key(it) {
                        TransactionView()
                        HorizontalDivider(thickness = 0.2.dp)
                    }
                }
            }
            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}

@Composable
internal fun Header(
    modifier: Modifier = Modifier,
    onNotificationClick: () -> Unit
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "Hey, John Doe",
            style = MaterialTheme.typography.headlineMedium
        )
        Surface(
            onClick = onNotificationClick,
            shape = MaterialTheme.shapes.medium,
            color = MaterialTheme.colorScheme.surfaceGray
        ) {
            Icon(
                painter = painterResource(Drawable.bellF),
                contentDescription = null,
                modifier = Modifier
                    .padding(10.dp)
                    .size(20.dp)
            )
        }
    }
}

@Composable
internal fun UserInfoSection(modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            contentColor = Color.White,
        )
    ) {
        Column(
            modifier = Modifier
                .clip(MaterialTheme.shapes.medium)
                .fillMaxWidth()
                .background(
                    Brush.horizontalGradient(
                        0.3f to Green200,
                        1f to Green400
                    )
                )
                .padding(24.dp)

        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    "Your balance",
                    style = MaterialTheme.typography.bodyMedium
                )
                Surface(
                    shape = MaterialTheme.shapes.medium,
                    color = Color.White.copy(alpha = 0.2f)
                ) {
                    Icon(
                        painter = painterResource(Drawable.moneys),
                        contentDescription = null,
                        modifier = Modifier
                            .padding(12.dp)
                            .size(32.dp)
                    )
                }
            }
            Text(
                text = "320,000",
                style = MaterialTheme.typography.headlineMedium
            )
            Text(
                text = "US Dollars",
                style = MaterialTheme.typography.labelMedium
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    RemitConnectTheme {
        HomeScreen()
    }
}