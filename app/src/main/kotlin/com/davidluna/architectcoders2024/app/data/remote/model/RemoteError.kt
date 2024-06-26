package com.davidluna.architectcoders2024.app.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteError(
    @SerialName("status_code")
    val statusCode: Int,
    @SerialName("status_message")
    val statusMessage: String,
    @SerialName("success")
    val success: Boolean
)