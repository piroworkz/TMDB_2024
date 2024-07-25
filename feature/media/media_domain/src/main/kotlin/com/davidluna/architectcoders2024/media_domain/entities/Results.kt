package com.davidluna.architectcoders2024.media_domain.entities

data class Results<T>(
    val page: Int,
    val results: List<T>,
    val totalPages: Int,
    val totalResults: Int
)