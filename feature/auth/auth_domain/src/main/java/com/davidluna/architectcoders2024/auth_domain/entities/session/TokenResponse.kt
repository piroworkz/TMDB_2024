package com.davidluna.architectcoders2024.auth_domain.entities.session

data class TokenResponse(
    val expiresAt: String,
    val requestToken: String,
    val success: Boolean
)