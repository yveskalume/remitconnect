package com.remitconnect.data.repository

import com.remitconnect.data.fake.datasources.recipient.FakeRecipientRemoteDataSourceFailure
import com.remitconnect.data.fake.datasources.recipient.FakeRecipientRemoteDataSourceSuccess
import com.remitconnect.domain.model.Recipient
import com.remitconnect.testing.rules.MainDispatcherRule
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class RecipientRepositoryImplTest {

    @get:Rule
    val dispatcherRule = MainDispatcherRule()

    private lateinit var recipientRepository: RecipientRepositoryImpl

    @Test
    fun `should return recipients successfully when remote data source returns success`() = runTest {

        val fakeRecipients = listOf(
            Recipient(id = "1", name = "John Doe", country = "Kenya", mobileWallet = "M-Pesa"),
            Recipient(id = "2", name = "Jane Smith", country = "Nigeria", mobileWallet = "Wave")
        )

        val fakeRemoteDataSource = FakeRecipientRemoteDataSourceSuccess(fakeRecipients)

        recipientRepository = RecipientRepositoryImpl(UnconfinedTestDispatcher(), fakeRemoteDataSource)

        val result = recipientRepository.getRecipients()

        assertTrue(result.isSuccess)
        assertEquals(fakeRecipients, result.getOrNull())
    }

    @Test
    fun `should return failure when remote data source returns failure`() = runTest {
        val errorMessage = "Failed to fetch recipients"
        val fakeRemoteDataSource = FakeRecipientRemoteDataSourceFailure(errorMessage)

        recipientRepository = RecipientRepositoryImpl(UnconfinedTestDispatcher(), fakeRemoteDataSource)

        val result = recipientRepository.getRecipients()

        assertTrue(result.isFailure)
        assertEquals(errorMessage, result.exceptionOrNull()?.message)
    }


}