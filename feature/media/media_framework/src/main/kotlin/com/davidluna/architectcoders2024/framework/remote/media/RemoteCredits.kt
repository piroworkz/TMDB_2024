package com.davidluna.architectcoders2024.framework.remote.media

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteCredits(
    @SerialName("cast")
    val cast: List<RemoteCast>,
    val id: Int
)