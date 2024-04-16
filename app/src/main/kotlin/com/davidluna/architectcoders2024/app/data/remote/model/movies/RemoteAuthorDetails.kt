package com.davidluna.architectcoders2024.app.data.remote.model.movies

import com.google.gson.annotations.SerializedName

data class RemoteAuthorDetails(
    @SerializedName("avatar_path")
    val avatarPath: String,
    val name: String,
    val rating: Double,
    val username: String
)