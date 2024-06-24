package com.davidluna.architectcoders2024.auth_data_framework.remote.model.authentication

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteUserAccountDetail(
    @SerialName("avatar")
    val avatar: RemoteAvatar,
    @SerialName("id")
    val id: Int,
    @SerialName("include_adult")
    val includeAdult: Boolean,
    @SerialName("name")
    val name: String,
    @SerialName("username")
    val username: String
)