package com.davidluna.architectcoders2024.app.data.remote.model.authentication

import com.google.gson.annotations.SerializedName

data class RemoteRequestSessionId(
    @SerializedName("request_token")
    val requestToken: String
)