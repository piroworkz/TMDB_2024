package com.davidluna.architectcoders2024.framework.remote.media

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteImage(
    @SerialName("file_path")
    val filePath: String,
)