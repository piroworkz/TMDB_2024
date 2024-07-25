package com.davidluna.architectcoders2024.framework.remote.media

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteImages(
    @SerialName("id")
    val id: Int,
    @SerialName("posters")
    val posters: List<RemoteImage>
)

