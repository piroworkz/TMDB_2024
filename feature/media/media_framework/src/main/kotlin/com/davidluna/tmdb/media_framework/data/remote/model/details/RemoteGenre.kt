package com.davidluna.tmdb.media_framework.data.remote.model.details

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteGenre(
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String
)