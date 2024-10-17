package com.davidluna.tmdb.media_domain.entities

data class Video(
    val id: String,
    val key: String,
    val site: String,
    val type: String,
    val order: Int = 100
)