package com.davidluna.tmdb.media_domain.entities.details

data class Cast(
    val castId: Int,
    val character: String,
    val name: String,
    val profilePath: String,
)