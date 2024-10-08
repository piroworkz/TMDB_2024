package com.davidluna.tmdb.videos_framework.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteVideo(
    @SerialName("id")
    val id: String,
    @SerialName("key")
    val key: String,
    @SerialName("site")
    val site: String,
    @SerialName("type")
    val type: String,
    @SerialName("order")
    val order: Int = 100
)
