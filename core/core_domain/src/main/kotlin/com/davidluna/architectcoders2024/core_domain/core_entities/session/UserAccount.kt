package com.davidluna.architectcoders2024.core_domain.core_entities.session

data class UserAccount(
    val id: Int,
    val name: String,
    val username: String,
    val avatarPath: String
)
