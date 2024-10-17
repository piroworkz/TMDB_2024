package com.davidluna.tmdb.media_data.framework.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteGenres(
    @SerialName("genres")
    val genres: List<RemoteGenre>
)