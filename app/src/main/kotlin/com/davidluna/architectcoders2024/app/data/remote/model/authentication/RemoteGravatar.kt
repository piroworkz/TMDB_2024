package com.davidluna.architectcoders2024.app.data.remote.model.authentication

import kotlinx.serialization.Serializable

@Serializable
data class RemoteGravatar(
    val hash: String
)