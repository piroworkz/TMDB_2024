package com.davidluna.tmdb.auth_domain.entities

data class UserAccount(
    val userId: Int,
    val name: String,
    val username: String,
    val avatarPath: String
)