package com.davidluna.architectcoders2024.app.data.remote.model.authentication

import kotlinx.serialization.Serializable

@Serializable
data class RemoteAvatar(
    val gravatar: RemoteGravatar,
    val tmdb: RemoteTmdb
)