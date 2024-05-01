package com.davidluna.architectcoders2024.domain.session

data class GuestSession(
    val expiresAt: String,
    val guestSessionId: String
)