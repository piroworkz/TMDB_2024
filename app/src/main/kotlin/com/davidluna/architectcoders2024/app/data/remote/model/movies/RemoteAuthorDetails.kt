package com.davidluna.architectcoders2024.app.data.remote.model.movies

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteAuthorDetails(
    @SerialName("avatar_path")
    val avatarPath: String,
    @SerialName("name")
    val name: String,
    @SerialName("rating")
    val rating: Double,
    @SerialName("username")
    val username: String
)