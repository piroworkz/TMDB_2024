package com.davidluna.architectcoders2024.app.data.remote.model.movies

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteVideos(
    @SerialName("id")
    val id: Int,
    @SerialName("results")
    val results: List<RemoteVideo>
)