package com.remitconnect.testing.data

import com.remitconnect.domain.model.Country
import com.remitconnect.domain.model.MobileWallet
import com.remitconnect.domain.model.Recipient

object FakeData {
    val recipients = listOf(
        Recipient(id = "1", name = "John Doe", country = "Senegal", mobileWallet = "Wave"),
        Recipient(
            id = "2",
            name = "Jane Smith",
            country = "Côte d'Ivoire",
            mobileWallet = "Orange Money"
        ),
        Recipient(id = "3", name = "Alice Johnson", country = "Morocco", mobileWallet = "CashPlus"),
        Recipient(id = "4", name = "Bob Brown", country = "Togo", mobileWallet = "Moov Togo"),
        Recipient(id = "5", name = "Eve White", country = "Ghana", mobileWallet = "Wave")
    )

    val countries = listOf(
        Country(id = "1", name = "Ghana", currencyCode = "GHS"),
        Country(id = "2", name = "Nigeria", currencyCode = "NGN"),
        Country(id = "3", name = "Kenya", currencyCode = "KES"),
        Country(id = "4", name = "South Africa", currencyCode = "ZAR"),
        Country(id = "5", name = "Uganda", currencyCode = "UGX"),
        Country(id = "6", name = "Tanzania", currencyCode = "TZS"),
        Country(id = "7", name = "Côte d'Ivoire", currencyCode = "XOF"),
        Country(id = "8", name = "Senegal", currencyCode = "XOF"),
        Country(id = "9", name = "Morocco", currencyCode = "MAD"),
        Country(id = "10", name = "Rwanda", currencyCode = "RWF")
    )

    val mobileWallets = listOf(
        MobileWallet(id = "1", name = "Wave"),
        MobileWallet(id = "2", name = "Orange Money"),
        MobileWallet(id = "3", name = "Moov Togo"),
        MobileWallet(id = "4", name = "CashPlus"),
    )
}