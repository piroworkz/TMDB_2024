package com.davidluna.architectcoders2024.media_domain.entities

data class Cast(
    val character: String,
    val id: Int,
    val name: String,
    val profilePath: String?
)