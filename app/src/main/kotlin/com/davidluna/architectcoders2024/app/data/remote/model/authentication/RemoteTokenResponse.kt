package com.davidluna.architectcoders2024.app.data.remote.model.authentication

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteTokenResponse(
    @SerialName("expires_at")
    val expiresAt: String,
    @SerialName("request_token")
    val requestToken: String,
    @SerialName("success")
    val success: Boolean
)