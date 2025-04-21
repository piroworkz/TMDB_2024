package com.davidluna.tmdb.auth_framework.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteAvatar(
    @SerialName("gravatar")
    val gravatar: RemoteGravatar,
    @SerialName("tmdb")
    val tmdb: RemoteTmdb
)

