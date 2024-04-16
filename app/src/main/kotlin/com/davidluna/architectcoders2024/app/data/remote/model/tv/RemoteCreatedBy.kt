package com.davidluna.architectcoders2024.app.data.remote.model.tv

import com.google.gson.annotations.SerializedName

data class RemoteCreatedBy(
    @SerializedName("credit_id")
    val creditId: String,
    val gender: Int,
    val id: Int,
    val name: String,
    @SerializedName("original_name")
    val originalName: String,
    @SerializedName("profile_path")
    val profilePath: String
)