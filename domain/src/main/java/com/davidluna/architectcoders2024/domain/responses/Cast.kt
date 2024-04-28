package com.davidluna.architectcoders2024.domain.responses

data class Cast(
    val character: String,
    val id: Int,
    val name: String,
    val profilePath: String?
)