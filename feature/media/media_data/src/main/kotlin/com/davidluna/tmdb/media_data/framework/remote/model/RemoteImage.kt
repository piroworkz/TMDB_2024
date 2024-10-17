package com.davidluna.tmdb.media_data.framework.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteImage(
    @SerialName("file_path")
    val filePath: String,
)