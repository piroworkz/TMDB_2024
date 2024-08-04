package com.davidluna.architectcoders2024.auth_framework.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteSessionIdResponse(
    @SerialName("session_id")
    val sessionId: String,
    @SerialName("success")
    val success: Boolean
)