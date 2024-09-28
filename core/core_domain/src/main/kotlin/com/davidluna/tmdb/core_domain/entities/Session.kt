package com.davidluna.tmdb.core_domain.entities

data class Session(
    val id: String,
    val guestSession: com.davidluna.tmdb.core_domain.entities.GuestSession
)
