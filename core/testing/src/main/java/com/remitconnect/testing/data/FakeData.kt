package com.remitconnect.testing.data

import com.remitconnect.domain.model.Recipient

object FakeData  {
    val recipients = listOf(
        Recipient(id = "1", name = "John Doe", country = "Senegal", mobileWallet = "Wave"),
        Recipient(id = "2", name = "Jane Smith", country = "Côte d'Ivoire", mobileWallet = "Orange Money"),
        Recipient(id = "3", name = "Alice Johnson", country = "Morocco", mobileWallet = "CashPlus"),
        Recipient(id = "4", name = "Bob Brown", country = "Togo", mobileWallet = "Moov Togo"),
        Recipient(id = "5", name = "Eve White", country = "Ghana", mobileWallet = "Wave")
    )
}