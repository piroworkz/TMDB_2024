package com.davidluna.architectcoders2024.app.data.remote.model.authentication

import com.google.gson.annotations.SerializedName

data class RemoteTokenResponse(
    @SerializedName("expires_at")
    val expiresAt: String,
    @SerializedName("request_token")
    val requestToken: String,
    @SerializedName("success")
    val success: Boolean
)