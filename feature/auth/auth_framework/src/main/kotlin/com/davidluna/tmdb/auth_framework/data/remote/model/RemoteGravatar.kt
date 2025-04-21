package com.davidluna.tmdb.auth_framework.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class RemoteGravatar(
    val hash: String
)