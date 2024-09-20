package com.remitconnect.feature.sendmoney.mobilewallets

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.testing.invoke
import com.remitconnect.domain.repository.MobileWalletRepository
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
class ChooseMobileWalletViewModelTest {

    @get:Rule
    val dispatcherRule = MainDispatcherRule()

    private val RECIPIENT_ID = "1"

    private lateinit var viewModel: ChooseMobileWalletViewModel
    private val mobileWalletRepository: MobileWalletRepository = mockk()
    private val savedStateHandle =
        SavedStateHandle(ChooseMobileWalletRoute(recipientId = RECIPIENT_ID))

    @Before
    fun setup() {
        viewModel = ChooseMobileWalletViewModel(savedStateHandle, mobileWalletRepository)
    }

    @Test
    fun `should emit loading state initially`() = runTest {
        assertEquals(ChooseMobileWalletUiState.Loading, viewModel.uiState.value)
    }

    @Test
    fun `should emit success state when mobile wallets are fetched successfully`() = runTest {
        val mobileWallets = FakeData.mobileWallets

        coEvery { mobileWalletRepository.getMobileWallets() } returns Result.success(mobileWallets)

        val collectJob = launch(UnconfinedTestDispatcher()) { viewModel.uiState.collect() }

        assertEquals(
            ChooseMobileWalletUiState.Success(
                mobileWallets = mobileWallets,
                recipientId = RECIPIENT_ID
            ),
            viewModel.uiState.value
        )

        collectJob.cancel()
    }

    @Test
    fun `should emit error state when fetching mobile wallets fails`() = runTest {
        val errorMessage = "Network error"

        coEvery { mobileWalletRepository.getMobileWallets() } returns Result.failure(
            Exception(
                errorMessage
            )
        )

        val collectJob = launch(UnconfinedTestDispatcher()) { viewModel.uiState.collect() }

        assertEquals(
            ChooseMobileWalletUiState.Error(errorMessage),
            viewModel.uiState.value
        )

        collectJob.cancel()
    }

}