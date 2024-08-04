package com.davidluna.architectcoders2024.media_framework.data.remote.media

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteGenre(
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String
)