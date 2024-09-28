package com.davidluna.tmdb.auth_domain.entities.session

data class TokenResponse(
    val expiresAt: String,
    val requestToken: String,
    val success: Boolean
)