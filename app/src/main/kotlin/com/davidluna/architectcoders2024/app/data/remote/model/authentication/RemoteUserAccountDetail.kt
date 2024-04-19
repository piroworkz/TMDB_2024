package com.davidluna.architectcoders2024.app.data.remote.model.authentication

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteUserAccountDetail(
    val avatar: RemoteAvatar,
    val id: Int,
    @SerialName("include_adult")
    val includeAdult: Boolean,
    val name: String,
    val username: String
)