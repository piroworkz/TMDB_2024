package com.davidluna.tmdb.media_domain.entities

data class Results<T>(
    val page: Int,
    val results: List<T>,
    val totalPages: Int,
    val totalResults: Int
)