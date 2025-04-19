package com.davidluna.tmdb.videos_framework.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteVideos(
    @SerialName("id")
    val id: Int,
    @SerialName("results")
    val results: List<RemoteVideo>
)