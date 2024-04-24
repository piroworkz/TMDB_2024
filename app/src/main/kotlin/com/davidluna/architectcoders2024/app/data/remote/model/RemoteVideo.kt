package com.davidluna.architectcoders2024.app.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class RemoteVideo(
    val id: String,
    val key: String,
    val official: Boolean,
    val site: String,
    val type: String,
    val order: Int = 100
)
