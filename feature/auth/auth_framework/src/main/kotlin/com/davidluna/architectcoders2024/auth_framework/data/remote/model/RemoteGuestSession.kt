package com.davidluna.tmdb.auth_framework.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteGuestSession(
    @SerialName("expires_at")
    val expiresAt: String,
    @SerialName("guest_session_id")
    val guestSessionId: String,
    @SerialName("success")
    val success: Boolean
)