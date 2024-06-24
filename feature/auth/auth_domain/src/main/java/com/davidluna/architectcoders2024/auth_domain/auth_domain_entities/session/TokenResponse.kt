package com.davidluna.architectcoders2024.auth_domain.auth_domain_entities.session

data class TokenResponse(
    val expiresAt: String,
    val requestToken: String,
    val success: Boolean
)