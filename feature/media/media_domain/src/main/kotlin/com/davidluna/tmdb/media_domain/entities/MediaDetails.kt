package com.davidluna.tmdb.media_domain.entities

data class MediaDetails(
    val genres: List<Genre>,
    val id: Int,
    val overview: String,
    val posterPath: String,
    val releaseDate: String,
    val tagline: String,
    val title: String,
    val voteAverage: Double,
    val hasVideo: Boolean,
)