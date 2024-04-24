package com.davidluna.architectcoders2024.app.data.remote.model.movies

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteMovie(
    @SerialName("id")
    val id: Int,
    @SerialName("poster_path")
    val posterPath: String?,
    @SerialName("title")
    val title: String,
)