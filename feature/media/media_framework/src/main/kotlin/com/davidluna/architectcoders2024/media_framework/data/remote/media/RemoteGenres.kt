package com.davidluna.architectcoders2024.media_framework.data.remote.media

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteGenres(
    @SerialName("genres")
    val genres: List<RemoteGenre>
)