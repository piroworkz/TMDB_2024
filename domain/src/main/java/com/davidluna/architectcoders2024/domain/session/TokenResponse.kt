package com.davidluna.architectcoders2024.domain.session

data class TokenResponse(
    val expiresAt: String,
    val requestToken: String,
    val success: Boolean
)