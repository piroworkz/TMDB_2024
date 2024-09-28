package com.davidluna.tmdb.media_framework.data.remote.media

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteImages(
    @SerialName("id")
    val id: Int,
    @SerialName("posters")
    val posters: List<RemoteImage>
)

