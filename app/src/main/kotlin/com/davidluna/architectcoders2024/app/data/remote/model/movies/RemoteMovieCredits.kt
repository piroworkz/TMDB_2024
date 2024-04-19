package com.davidluna.architectcoders2024.app.data.remote.model.movies

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteMovieCredits(
    @SerialName("cast")
    val cast: List<RemoteCast>,
    @SerialName("crew")
    val crew: List<RemoteCrew>,
    @SerialName("id")
    val id: Int
)