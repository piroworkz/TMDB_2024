package com.davidluna.architectcoders2024.domain.responses.common

data class Error(
    val statusCode: Int,
    val statusMessage: String,
    val success: Boolean
)