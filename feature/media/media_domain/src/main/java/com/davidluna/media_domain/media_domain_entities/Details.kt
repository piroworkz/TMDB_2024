package com.davidluna.media_domain.media_domain_entities

data class Details(
    val genres: List<Genre>,
    val id: Int,
    val overview: String,
    val posterPath: String,
    val releaseDate: String,
    val tagline: String,
    val title: String,
    val voteAverage: Double
)