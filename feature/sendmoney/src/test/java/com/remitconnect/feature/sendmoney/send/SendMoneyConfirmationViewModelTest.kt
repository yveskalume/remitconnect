package com.remitconnect.feature.sendmoney.send

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.testing.invoke
import com.remitconnect.domain.repository.CountryRepository
import com.remitconnect.domain.repository.MobileWalletRepository
import com.remitconnect.domain.repository.RecipientRepository
import com.remitconnect.testing.data.FakeData
import com.remitconnect.testing.rules.MainDispatcherRule
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(RobolectricTestRunner::class)
class SendMoneyConfirmationViewModelTest {

    private val RECIPIENT_ID = "1"
    private val MOBILE_WALLET_ID = "2"

    @get:Rule
    val dispatcherRule = MainDispatcherRule()

    private lateinit var viewModel: SendMoneyConfirmationViewModel
    private val recipientRepository: RecipientRepository = mockk()
    private val countryRepository: CountryRepository = mockk()
    private val mobileWalletRepository: MobileWalletRepository = mockk()
    private val route = SendMoneyConfirmationRoute(RECIPIENT_ID, MOBILE_WALLET_ID)
    private val savedStateHandle = SavedStateHandle(route)


    @Before
    fun setup() {
        viewModel = SendMoneyConfirmationViewModel(
            savedStateHandle,
            recipientRepository,
            countryRepository,
            mobileWalletRepository
        )
    }

    @Test
    fun `should emit loading state initially`() = runTest {
        assertEquals(SendMoneyConfirmationUiState.Loading, viewModel.uiState.value)
    }

    @Test
    fun `should emit success state when valid amount is entered and all data is retrieved`() =
        runTest {
            val recipients = FakeData.recipients
            val countries = FakeData.countries
            val wallets = FakeData.mobileWallets

            val chosenRecipient = recipients.first { it.id == RECIPIENT_ID }
            val recipientCountry = countries.first { it.name == chosenRecipient.country }
            val chosenWallet = wallets.first { it.id == MOBILE_WALLET_ID }

            coEvery { recipientRepository.getRecipients() } returns Result.success(recipients)
            coEvery { countryRepository.getCountries() } returns Result.success(countries)
            coEvery { mobileWalletRepository.getMobileWallets() } returns Result.success(wallets)

            // Simulate changing the amount
            viewModel.handleAction(SendMoneyConfirmationAction.AmountChanged("100.00"))

            val collectJob = launch(UnconfinedTestDispatcher()) { viewModel.uiState.collect() }

            assertEquals(
                SendMoneyConfirmationUiState.Success(
                    amount = 100.00,
                    recipientCurrency = recipientCountry.currencyCode,
                    canSend = true,
                    recipient = chosenRecipient,
                    mobileWallet = chosenWallet,
                    monecoFees = 0.0,
                    transferFees = 0.0,
                    conversionRate = 655.94
                ),
                viewModel.uiState.value
            )

            collectJob.cancel()
        }

    @Test
    fun `should emit error state when recipient is not found`() = runTest {
        coEvery { recipientRepository.getRecipients() } returns Result.success(emptyList())

        viewModel.handleAction(SendMoneyConfirmationAction.AmountChanged("100.00"))

        val collectJob = launch(UnconfinedTestDispatcher()) {
            viewModel.uiState.collect()
        }

        assertEquals(
            SendMoneyConfirmationUiState.Error("Recipient not found"),
            viewModel.uiState.value
        )

        collectJob.cancel()
    }

    @Test
    fun `should emit error state when country is not found`() = runTest {
        coEvery { recipientRepository.getRecipients() } returns Result.success(FakeData.recipients)
        coEvery { countryRepository.getCountries() } returns Result.success(emptyList())
        coEvery { mobileWalletRepository.getMobileWallets() } returns Result.success(FakeData.mobileWallets)

        viewModel.handleAction(SendMoneyConfirmationAction.AmountChanged("100.00"))

        val collectJob = launch(UnconfinedTestDispatcher()) {
            viewModel.uiState.collect()
        }

        assertEquals(
            SendMoneyConfirmationUiState.Error("Country not found"),
            viewModel.uiState.value
        )

        collectJob.cancel()
    }

    @Test
    fun `should emit error state when mobile wallet is not found`() = runTest {
        val recipients = FakeData.recipients
        val countries = FakeData.countries

        coEvery { recipientRepository.getRecipients() } returns Result.success(recipients)
        coEvery { countryRepository.getCountries() } returns Result.success(countries)
        coEvery { mobileWalletRepository.getMobileWallets() } returns Result.success(emptyList())

        viewModel.handleAction(SendMoneyConfirmationAction.AmountChanged("100.00"))

        val collectJob = launch(UnconfinedTestDispatcher()) {
            viewModel.uiState.collect()
        }

        assertEquals(
            SendMoneyConfirmationUiState.Error("Mobile wallet not found"),
            viewModel.uiState.value
        )

        collectJob.cancel()
    }

    @Test
    fun `should not update amount if invalid input is entered`() = runTest {
        viewModel.handleAction(SendMoneyConfirmationAction.AmountChanged("invalid"))

        assertEquals("", viewModel.amountText.value)
    }

    @Test
    fun `should update amount if valid input is entered`() = runTest {
        viewModel.handleAction(SendMoneyConfirmationAction.AmountChanged("50.00"))

        assertEquals("50.00", viewModel.amountText.value)
    }

}