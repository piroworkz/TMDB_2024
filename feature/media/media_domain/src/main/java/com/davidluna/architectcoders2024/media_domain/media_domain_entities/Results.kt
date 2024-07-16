package com.davidluna.architectcoders2024.media_domain.media_domain_entities

data class Results<T>(
    val page: Int,
    val results: List<T>,
    val totalPages: Int,
    val totalResults: Int
)