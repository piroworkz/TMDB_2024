package com.davidluna.architectcoders2024.framework.remote.model.authentication

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteTmdb(
    @SerialName("avatar_path")
    val avatarPath: String
)