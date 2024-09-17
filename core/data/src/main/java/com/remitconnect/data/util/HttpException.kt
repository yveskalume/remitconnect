package com.remitconnect.data.util

data class HttpException(override val message: String) : Exception(message)