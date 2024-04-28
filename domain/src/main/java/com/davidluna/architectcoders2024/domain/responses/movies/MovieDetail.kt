package com.davidluna.architectcoders2024.domain.responses.movies

data class MovieDetail(
    val genres: List<Genre>,
    val id: Int,
    val overview: String,
    val posterPath: String,
    val releaseDate: String,
    val tagline: String,
    val title: String,
    val voteAverage: Double
)