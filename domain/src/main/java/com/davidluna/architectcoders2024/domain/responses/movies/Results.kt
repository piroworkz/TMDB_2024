package com.davidluna.architectcoders2024.domain.responses.movies

data class Results<T>(
    val page: Int,
    val results: List<T>,
    val totalPages: Int,
    val totalResults: Int
)