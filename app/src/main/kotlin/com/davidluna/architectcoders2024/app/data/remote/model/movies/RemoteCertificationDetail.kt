package com.davidluna.architectcoders2024.app.data.remote.model.movies
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteCertificationDetail(
    @SerialName("certification")
    val certification: String,
    @SerialName("meaning")
    val meaning: String,
    @SerialName("order")
    val order: Int
)