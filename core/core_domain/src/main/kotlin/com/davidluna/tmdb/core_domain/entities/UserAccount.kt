package com.davidluna.tmdb.core_domain.entities

data class UserAccount(
    val id: Int,
    val name: String,
    val username: String,
    val avatarPath: String
)
