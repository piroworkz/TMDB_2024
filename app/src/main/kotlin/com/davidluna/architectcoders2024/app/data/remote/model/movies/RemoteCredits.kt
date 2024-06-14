package com.davidluna.architectcoders2024.app.data.remote.model.movies

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteCredits(
    @SerialName("cast")
    val cast: List<RemoteCast>,
    val id: Int
)