package com.remitconnect.data.util

internal object ServerEndpoints {
    private const val BASE_URL = "https://my-json-server.typicode.com/MonecoHQ/fake-api"
    const val COUNTRIES = "$BASE_URL/countries"
    const val RECIPIENTS = "$BASE_URL/recipients"
    const val WALLETS = "$BASE_URL/wallets"
}