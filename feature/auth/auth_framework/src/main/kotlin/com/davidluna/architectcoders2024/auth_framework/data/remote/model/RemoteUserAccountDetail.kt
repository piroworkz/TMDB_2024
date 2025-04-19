package com.davidluna.tmdb.auth_framework.data.remote.model

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