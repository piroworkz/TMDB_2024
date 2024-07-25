package com.davidluna.architectcoders2024.framework.remote.media

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteGenres(
    @SerialName("genres")
    val genres: List<RemoteGenre>
)