package com.davidluna.architectcoders2024.app.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class RemoteVideos(
    val id: Int,
    val results: List<RemoteVideo>
)