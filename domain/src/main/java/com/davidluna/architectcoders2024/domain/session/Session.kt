package com.davidluna.architectcoders2024.domain.session

data class Session(
    val auth: SessionId,
    val user: UserAccount
)