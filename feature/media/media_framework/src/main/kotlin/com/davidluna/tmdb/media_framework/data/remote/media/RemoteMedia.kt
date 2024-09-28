package com.davidluna.tmdb.media_framework.data.remote.media

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteMedia(
    @SerialName("id")
    val id: Int,
    @SerialName("poster_path")
    val posterPath: String?,
    @SerialName("title")
    val title: String? = null,
    @SerialName("name")
    val name: String? = null,
)
