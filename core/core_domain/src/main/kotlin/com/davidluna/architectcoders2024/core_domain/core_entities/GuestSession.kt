package com.davidluna.architectcoders2024.core_domain.core_entities

data class GuestSession(
    val expiresAt: String,
    val id: String,
    val isGuest: Boolean = true
)