package com.davidluna.architectcoders2024.app.data.remote.model.movies

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteGenre(
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String
)