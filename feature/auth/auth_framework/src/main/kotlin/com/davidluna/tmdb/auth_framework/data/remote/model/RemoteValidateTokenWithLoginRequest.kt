package com.davidluna.tmdb.auth_framework.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteValidateTokenWithLoginRequest(
    @SerialName("request_token")
    val requestToken: String,
    @SerialName("username")
    val username: String,
    @SerialName("password")
    val password: String
)