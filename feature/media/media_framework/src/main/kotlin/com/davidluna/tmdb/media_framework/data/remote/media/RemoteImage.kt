package com.davidluna.tmdb.media_framework.data.remote.media

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteImage(
    @SerialName("file_path")
    val filePath: String,
)