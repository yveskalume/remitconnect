package com.remitconnect.feature.sendmoney.recipients


import com.remitconnect.domain.model.Recipient
import com.remitconnect.domain.repository.RecipientRepository
import com.remitconnect.testing.data.FakeData
import com.remitconnect.testing.rules.MainDispatcherRule
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class ChooseRecipientViewModelTest {

    @get:Rule
    val dispatcherRule = MainDispatcherRule()

    private lateinit var viewModel: ChooseRecipientViewModel
    private val recipientRepository: RecipientRepository = mockk()

    @Before
    fun setup() {
        viewModel = ChooseRecipientViewModel(recipientRepository)
    }

    @Test
    fun `should emit loading state initially`() = runTest {
        val uiState = viewModel.uiState.value
        assertEquals(ChooseRecipientUiState.Loading, uiState)
    }

    @Test
    fun `should emit success state when recipients are fetched`() = runTest {
        val recipients = FakeData.recipients

        coEvery { recipientRepository.getRecipients() } returns Result.success(recipients)

        val collectJob = launch(UnconfinedTestDispatcher()) { viewModel.uiState.collect() }

        assertEquals(ChooseRecipientUiState.Success(recipients), viewModel.uiState.value)

        collectJob.cancel()
    }

    @Test
    fun `should filter recipients by search text`() = runTest {
        val recipients = listOf(
            Recipient(id = "1", name = "John Doe", country = "Senegal", mobileWallet = "Wave"),
            Recipient(
                id = "2",
                name = "Jane Smith",
                country = "Côte d'Ivoire",
                mobileWallet = "Orange Money"
            ),
            Recipient(
                id = "3",
                name = "Alice John",
                country = "Morocco",
                mobileWallet = "CashPlus"
            ),
            Recipient(id = "4", name = "Bob Brown", country = "Togo", mobileWallet = "Moov Togo"),
            Recipient(id = "5", name = "Eve White", country = "Ghana", mobileWallet = "Wave")
        )

        coEvery { recipientRepository.getRecipients() } returns Result.success(recipients)

        viewModel.handleAction(ChooseRecipientAction.SearchTextChange("John"))

        val collectJob = launch(UnconfinedTestDispatcher()) { viewModel.uiState.collect() }

        val expected = ChooseRecipientUiState.Success(
            listOf(
                Recipient(id = "1", name = "John Doe", country = "Senegal", mobileWallet = "Wave"),
                Recipient(
                    id = "3",
                    name = "Alice John",
                    country = "Morocco",
                    mobileWallet = "CashPlus"
                )
            )
        )
        assertEquals(
            expected,
            viewModel.uiState.value
        )

        collectJob.cancel()
    }

    @Test
    fun `should return all recipients when search text is blank`() = runTest {
        val recipients = FakeData.recipients
        coEvery { recipientRepository.getRecipients() } returns Result.success(recipients)

        viewModel.handleAction(ChooseRecipientAction.SearchTextChange(""))

        val collectJob = launch(UnconfinedTestDispatcher()) { viewModel.uiState.collect() }

        assertEquals(ChooseRecipientUiState.Success(recipients), viewModel.uiState.value)

        collectJob.cancel()
    }

    @Test
    fun `should emit error state when repository fails`() = runTest {
        coEvery { recipientRepository.getRecipients() } returns Result.failure(Exception("Error fetching recipients"))

        val collectJob = launch(UnconfinedTestDispatcher()) { viewModel.uiState.collect() }

        assertEquals(
            ChooseRecipientUiState.Error("Error fetching recipients"),
            viewModel.uiState.value
        )

        collectJob.cancel()
    }
}
