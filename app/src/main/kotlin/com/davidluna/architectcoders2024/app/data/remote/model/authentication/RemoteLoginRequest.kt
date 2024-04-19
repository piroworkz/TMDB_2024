package com.davidluna.architectcoders2024.app.data.remote.model.authentication

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteLoginRequest(
    @SerialName("request_token")
    val requestToken: String
)