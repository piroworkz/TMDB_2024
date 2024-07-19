package com.davidluna.architectcoders2024.media_domain.media_domain_entities

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