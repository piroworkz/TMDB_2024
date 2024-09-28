package com.davidluna.tmdb.media_framework.data.remote.media

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteCredits(
    @SerialName("cast")
    val cast: List<RemoteCast>,
    @SerialName("id")
    val id: Int,
)