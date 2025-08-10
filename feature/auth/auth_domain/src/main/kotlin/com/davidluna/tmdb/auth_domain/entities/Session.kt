package com.davidluna.tmdb.auth_domain.entities

data class Session(
    val sessionId: String,
    val isGuest: Boolean,
    val expiresAt: String?
)