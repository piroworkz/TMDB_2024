package com.davidluna.tmdb.auth_domain.entities

data class LoginRequest(
    val username: String = String(),
    val password: String = String()
)