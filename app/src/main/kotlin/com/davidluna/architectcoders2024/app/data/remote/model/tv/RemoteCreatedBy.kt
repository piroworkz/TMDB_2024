package com.davidluna.architectcoders2024.app.data.remote.model.tv

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteCreatedBy(
    @SerialName("credit_id")
    val creditId: String,
    @SerialName("gender")
    val gender: Int,
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String,
    @SerialName("original_name")
    val originalName: String,
    @SerialName("profile_path")
    val profilePath: String
)