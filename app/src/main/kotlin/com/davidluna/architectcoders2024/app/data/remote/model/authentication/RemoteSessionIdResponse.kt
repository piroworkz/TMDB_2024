package com.davidluna.architectcoders2024.app.data.remote.model.authentication

import com.google.gson.annotations.SerializedName

data class RemoteSessionIdResponse(
    @SerializedName("session_id")
    val sessionId: String,
    @SerializedName("success")
    val success: Boolean
)