package com.davidluna.architectcoders2024.app.data.remote.model.movies

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteDates(
    @SerialName("maximum")
    val maximum: String,
    @SerialName("minimum")
    val minimum: String
)