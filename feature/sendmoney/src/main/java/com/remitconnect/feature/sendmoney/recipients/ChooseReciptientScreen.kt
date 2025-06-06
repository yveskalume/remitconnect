package com.remitconnect.feature.sendmoney.recipients

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.remitconnect.designsystem.resources.Drawable
import com.remitconnect.designsystem.theme.RemitConnectTheme
import com.remitconnect.designsystem.theme.iconBackground
import com.remitconnect.designsystem.theme.labelGray
import com.remitconnect.designsystem.theme.surfaceGray
import com.remitconnect.designsystem.views.ContactItemView
import com.remitconnect.designsystem.views.SearchTextFieldView
import com.remitconnect.designsystem.views.TabView
import com.remitconnect.domain.model.Recipient
import com.remitconnect.feature.sendmoney.R


private val recipientTabs = mapOf(
    0 to "Previous recipients",
    1 to "New recipient"
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun ChooseRecipientScreen(
    searchText: String,
    onAction: (ChooseRecipientAction) -> Unit,
    uiState: ChooseRecipientUiState
) {
    var selectedTabIndex by rememberSaveable {
        mutableIntStateOf(0)
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
                        onClick = { onAction(ChooseRecipientAction.BackActionClick) },
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
            AnimatedVisibility(
                visible = selectedTabIndex == 1,
                enter = slideInVertically { it / 2 },
                exit = slideOutVertically { it / 2 },
            ) {
                Surface(
                    color = MaterialTheme.colorScheme.background,
                    shadowElevation = 8.dp,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Button(
                        onClick = {
                            onAction(
                                ChooseRecipientAction.CreateNewRecipient(
                                    "",
                                    "",
                                    ""
                                )
                            )
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
                .padding(bottom = 24.dp)
        ) {
            Text(
                text = "Who are you sending to?",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier
                    .padding(bottom = 24.dp, top = 8.dp)
                    .padding(horizontal = 24.dp)
            )

            TabView(
                items = remember { recipientTabs.values.toList() },
                selectedItemIndex = selectedTabIndex,
                onClick = { index ->
                    selectedTabIndex = index
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
            )

            var text by remember {
                mutableStateOf("")
            }

            SearchTextFieldView(
                value = searchText,
                onValueChange = {
                    text = it
                    onAction(ChooseRecipientAction.SearchTextChange(it))
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp)
                    .padding(horizontal = 24.dp),
                placeholder = {
                    Text(text = "Search...")
                },
                leadingIcon = {
                    Icon(imageVector = Icons.Rounded.Search, contentDescription = null)
                }
            )

            when (uiState) {
                is ChooseRecipientUiState.Error -> {
                    Text(
                        text = uiState.message,
                        modifier = Modifier
                            .padding(24.dp)
                            .align(Alignment.CenterHorizontally),
                        style = MaterialTheme.typography.bodyMedium
                    )
                }

                is ChooseRecipientUiState.Loading -> {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(top = 24.dp)
                    )
                }

                is ChooseRecipientUiState.Success -> {
                    AnimatedContent(targetState = selectedTabIndex, label = "") { index ->
                        when (index) {
                            0 -> PreviousRecipientsTabContent(
                                recipients = uiState.recipients,
                                onRecipientClick = { recipient: Recipient ->
                                    onAction(
                                        ChooseRecipientAction.SelectRecipient(
                                            recipient
                                        )
                                    )
                                }
                            )

                            1 -> NewRecipientTabContent()
                        }
                    }
                }
            }

        }
    }
}

@Composable
private fun PreviousRecipientsTabContent(
    recipients: List<Recipient>,
    onRecipientClick: (Recipient) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        if (recipients.isNotEmpty()) {
            Text(
                text = "Contacts on your phone",
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier
                    .padding(top = 32.dp, bottom = 16.dp)
                    .padding(horizontal = 24.dp),
                fontWeight = FontWeight.Medium
            )

            recipients.forEach { recipient ->
                key(recipient.id) {
                    HorizontalDivider(thickness = 0.4.dp)
                    ContactItemView(
                        name = recipient.name,
                        mobileWallet = recipient.mobileWallet,
                        onClick = { onRecipientClick(recipient) },
                        modifier = Modifier.padding(horizontal = 24.dp)
                    )
                }
            }
            HorizontalDivider(thickness = 0.4.dp)
        } else {
            Image(
                painter = painterResource(Drawable.emptyStateIllustration),
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .fillMaxWidth()
                    .padding(top = 48.dp)
                    .padding(horizontal = 41.dp),
            )
        }

    }
}


@Composable
private fun NewRecipientTabContent(modifier: Modifier = Modifier) {

    val keyboardController = LocalSoftwareKeyboardController.current

    var firstName by rememberSaveable {
        mutableStateOf("")
    }

    var lastName by rememberSaveable {
        mutableStateOf("")
    }

    var phoneNumber by rememberSaveable {
        mutableStateOf("")
    }

    Column(
        modifier = modifier.padding(horizontal = 24.dp)
    ) {
        Text(
            text = "Country",
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier
                .padding(top = 32.dp, bottom = 34.dp),
            fontWeight = FontWeight.Medium
        )

        OutlinedCard(
            modifier = Modifier
                .height(56.dp)
                .fillMaxWidth(),
            shape = MaterialTheme.shapes.extraSmall
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(R.drawable.benin),
                    contentDescription = null,
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(24.dp)
                )
                Text(
                    text = "Benin",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(start = 8.dp)
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = "+229",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.labelGray
                )
                Icon(
                    imageVector = Icons.Rounded.KeyboardArrowDown,
                    contentDescription = null
                )

            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .background(MaterialTheme.colorScheme.iconBackground)
                .border(1.dp, MaterialTheme.colorScheme.primary, MaterialTheme.shapes.small)
                .padding(horizontal = 24.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally)
        ) {
            Icon(
                painter = painterResource(Drawable.document),
                contentDescription = null
            )
            Text(
                text = "Choose a contact",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            HorizontalDivider(modifier = Modifier.weight(1f))
            Text(
                text = "OR ADD MANUALLY",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.labelGray,
            )
            HorizontalDivider(modifier = Modifier.weight(1f))
        }

        Text(
            text = "Phone number",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp, bottom = 8.dp)
        )

        OutlinedTextField(
            value = phoneNumber,
            onValueChange = { phoneNumber = it },
            textStyle = MaterialTheme.typography.bodyMedium,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Phone,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(onDone = {
                keyboardController?.hide()
            }),
            modifier = Modifier.fillMaxWidth(),
        )

        Text(
            text = "First Name",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp, bottom = 8.dp)
        )

        OutlinedTextField(
            value = firstName,
            onValueChange = { firstName = it },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(onDone = {
                keyboardController?.hide()
            }),
            textStyle = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.fillMaxWidth(),
        )

        Text(
            text = "Last Name",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp, bottom = 8.dp)
        )

        OutlinedTextField(
            value = lastName,
            onValueChange = { lastName = it },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(onDone = {
                keyboardController?.hide()
            }),
            textStyle = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.fillMaxWidth(),
        )
    }
}

@Preview
@Composable
private fun ChooseRecipientScreenPreview() {
    RemitConnectTheme {
        ChooseRecipientScreen(
            searchText = "",
            uiState = ChooseRecipientUiState.Loading,
            onAction = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviousRecipientTabContentPreview() {
    RemitConnectTheme {
        PreviousRecipientsTabContent(
            onRecipientClick = {},
            recipients = emptyList()
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun NewRecipientTabContentPreview() {
    RemitConnectTheme {
        NewRecipientTabContent()
    }
}