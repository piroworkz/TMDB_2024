package com.davidluna.tmdb.media_domain.entities.details

data class MediaDetails(
    val id: Int,
    val title: String,
    val releaseDate: String,
    val runtime: Int,
    val posterPath: String,
    val backdropPath: String,
    val overview: String,
    val tagline: String,
    val hasVideo: Boolean,
    val voteAverage: Float,
    val genres: List<Genre>,
    val castList: List<Cast>,
    val images: List<Image>
) {
    val voteAveragePercentage: String
        get() = "${(voteAverage * 10).toInt()}%"

    val score: Float
        get() = voteAverage / 10
}