package com.davidluna.architectcoders2024.app.data.remote.model.authentication

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteSessionIdResponse(
    @SerialName("session_id")
    val sessionId: String,
    @SerialName("success")
    val success: Boolean
)