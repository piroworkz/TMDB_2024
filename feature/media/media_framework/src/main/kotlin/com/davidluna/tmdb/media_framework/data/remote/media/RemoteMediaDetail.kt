package com.davidluna.tmdb.media_framework.data.remote.media

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteMediaDetail(
    @SerialName("genres")
    val genres: List<RemoteGenre>,
    @SerialName("id")
    val id: Int,
    @SerialName("overview")
    val overview: String,
    @SerialName("poster_path")
    val posterPath: String?,
    @SerialName("release_date")
    val releaseDate: String? = null,
    @SerialName("first_air_date")
    val firstAirDate: String? = null,
    @SerialName("tagline")
    val tagline: String,
    @SerialName("title")
    val title: String? = null,
    @SerialName("name")
    val name: String? = null,
    @SerialName("vote_average")
    val voteAverage: Double,
    @SerialName("video")
    val video: Boolean = true
)