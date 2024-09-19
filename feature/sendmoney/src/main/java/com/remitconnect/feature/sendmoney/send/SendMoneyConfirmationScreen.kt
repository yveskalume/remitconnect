package com.remitconnect.feature.sendmoney.send

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.remitconnect.designsystem.resources.Drawable
import com.remitconnect.designsystem.theme.Gray200
import com.remitconnect.designsystem.theme.RemitConnectTheme
import com.remitconnect.designsystem.theme.labelGray
import com.remitconnect.designsystem.theme.surfaceGray
import com.remitconnect.designsystem.views.HorizontalDashedDivider

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun SendMoneyConfirmationScreen(
    amountText: String,
    uiState: SendMoneyConfirmationUiState,
    isProcessing: Boolean,
    onAction: (SendMoneyConfirmationAction) -> Unit
) {

    var isDialogVisible by rememberSaveable {
        mutableStateOf(false)
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
                        onClick = { onAction(SendMoneyConfirmationAction.NavigateBack) },
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
        },
        bottomBar = {
            Surface(
                color = MaterialTheme.colorScheme.background,
                shadowElevation = 8.dp,
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(
                    enabled = !isProcessing && uiState is SendMoneyConfirmationUiState.Success && uiState.canSend,
                    onClick = { isDialogVisible = true },
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
        val columnScrollState = rememberScrollState()

        Column(
            modifier = Modifier
                .verticalScroll(columnScrollState)
                .fillMaxWidth()
                .wrapContentHeight()
                .consumeWindowInsets(contentPadding)
                .padding(contentPadding)
                .padding(horizontal = 24.dp)
        ) {
            Text(
                text = "Send money",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier
                    .padding(bottom = 24.dp, top = 8.dp)
            )
            when (uiState) {
                is SendMoneyConfirmationUiState.Error -> {
                    Text(
                        text = uiState.message,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                }

                SendMoneyConfirmationUiState.Loading -> {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                }

                is SendMoneyConfirmationUiState.Success -> {
                    AmountSection(
                        amountText = amountText,
                        onTextChange = { onAction(SendMoneyConfirmationAction.AmountChanged(it)) }
                    )
                    RemittanceInfoSection()
                    FeesSection(uiState = uiState)
                }
            }
            if (uiState is SendMoneyConfirmationUiState.Success) {
                ConfirmationDialog(
                    uiState = uiState,
                    isVisible = isDialogVisible,
                    onContinueClick = { onAction(SendMoneyConfirmationAction.Confirm) },
                    onDismissRequest = {
                        isDialogVisible = false
                    }
                )
            }
        }
    }
}

@Composable
private fun AmountSection(
    amountText: String,
    onTextChange: (String) -> Unit
) {
    Text(
        text = "How much are you sending ?",
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier
            .padding(bottom = 8.dp)
    )

    val softKeyboardController = LocalSoftwareKeyboardController.current

    OutlinedCard(
        modifier = Modifier.fillMaxWidth()
    ) {
        TextField(
            value = amountText,
            onValueChange = onTextChange,
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color.Transparent,
                focusedContainerColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
            ),
            textStyle = MaterialTheme.typography.bodyLarge,
            placeholder = {
                Text("00", style = MaterialTheme.typography.bodyLarge)
            },
            trailingIcon = {
                Text(
                    text = "EUR",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.labelGray
                )
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            singleLine = true,
            keyboardActions = KeyboardActions(
                onDone = {
                    softKeyboardController?.hide()
                }
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 8.dp)
        )
        Row(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.surfaceGray)
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Your current balance is ",
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.labelGray
            )
            Text(
                text = "230 EUR",
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.labelGray,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 8.dp)
            )
        }
    }
}

@Composable
private fun RemittanceInfoSection() {
    Text(
        text = "Yearly free remittances",
        style = MaterialTheme.typography.bodyMedium,
        fontWeight = FontWeight.Medium,
        modifier = Modifier
            .padding(top = 32.dp, bottom = 4.dp)
    )
    Text(
        text = "Remittances are free with Moneco until you reach your limit, " +
                "which resets every year.",
        style = MaterialTheme.typography.bodySmall,
        color = MaterialTheme.colorScheme.labelGray,
        modifier = Modifier
            .padding(bottom = 4.dp)
    )

    Text(
        text = "Check number of free remittance remaining",
        style = MaterialTheme.typography.bodySmall,
        fontWeight = FontWeight.Bold,
        color = Color(0xFF1B98E0),
        modifier = Modifier
            .padding(bottom = 32.dp)
            .clickable { }
    )
}

@Composable
private fun FeesSection(
    uiState: SendMoneyConfirmationUiState.Success
) {
    Text(
        text = "Fees breakdown",
        style = MaterialTheme.typography.bodyMedium,
        fontWeight = FontWeight.Medium,
        modifier = Modifier
            .padding(bottom = 16.dp)
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Moneco fees",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.labelGray
        )
        Text(
            text = "${uiState.monecoFees} EUR",
            style = MaterialTheme.typography.bodySmall,
        )
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Transfer fees",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.labelGray
        )
        Text(
            text = "${uiState.transferFees} EUR",
            style = MaterialTheme.typography.bodySmall,
        )
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Conversion rate",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.labelGray
        )
        Text(
            text = "${uiState.conversionRate} ${uiState.recipientCurrency}",
            style = MaterialTheme.typography.bodySmall,
        )
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "You’ll spend in total",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.labelGray
        )
        Text(
            text = "${uiState.totalInActualCurrency} EUR",
            style = MaterialTheme.typography.bodySmall,
        )
    }

    HorizontalDashedDivider()

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 24.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Recipient gets",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.labelGray
        )
        Text(
            text = "${uiState.amountGotByRecipient} ${uiState.recipientCurrency}",
            style = MaterialTheme.typography.bodyLarge,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ConfirmationDialog(
    uiState: SendMoneyConfirmationUiState.Success,
    isVisible: Boolean,
    onContinueClick: () -> Unit,
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier
) {
    if (isVisible) {
        ModalBottomSheet(
            modifier = modifier,
            onDismissRequest = onDismissRequest,
            dragHandle = {
                BottomSheetDefaults.DragHandle(
                    width = 72.dp,
                    height = 6.dp,
                    color = Gray200
                )
            }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp)
            ) {
                Text(
                    text = "Confirm transfer",
                    style = MaterialTheme.typography.headlineMedium
                )
                Text(
                    text = "You’re sending",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.labelGray,
                    modifier = Modifier.padding(top = 16.dp)
                )
                Text(
                    text = "${uiState.amountGotByRecipient} ${uiState.recipientCurrency}",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(top = 4.dp)
                )

                Text(
                    text = "To",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.labelGray,
                    modifier = Modifier.padding(top = 16.dp)
                )
                Text(
                    text = uiState.recipient.name,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(top = 4.dp)
                )

                Text(
                    text = "Via",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.labelGray,
                    modifier = Modifier.padding(top = 16.dp)
                )
                Text(
                    text = uiState.mobileWallet.name,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(top = 4.dp)
                )


                Button(
                    onClick = onContinueClick,
                    modifier = Modifier
                        .padding(vertical = 32.dp)
                        .fillMaxWidth()
                        .height(56.dp),
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
    }
}

@Preview
@Composable
private fun SendMoneyConfirmationScreenPreview() {
    RemitConnectTheme {
        SendMoneyConfirmationScreen(
            uiState = SendMoneyConfirmationUiState.Loading,
            amountText = "100",
            isProcessing = false,
            onAction = {}
        )
    }
}