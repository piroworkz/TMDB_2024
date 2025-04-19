package com.davidluna.tmdb.media_domain.entities

data class Cast(
    val character: String,
    val id: Int,
    val name: String,
    val profilePath: String?
)