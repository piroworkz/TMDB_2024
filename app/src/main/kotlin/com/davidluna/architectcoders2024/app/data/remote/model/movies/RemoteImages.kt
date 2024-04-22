package com.davidluna.architectcoders2024.app.data.remote.model.movies

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteImages(
    @SerialName("backdrops")
    val backdrops: List<RemoteImage>,
    @SerialName("id")
    val id: Int,
    @SerialName("logos")
    val logos: List<RemoteImage>,
    @SerialName("posters")
    val posters: List<RemoteImage>
)

