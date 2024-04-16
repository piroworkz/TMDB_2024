package com.davidluna.architectcoders2024.app.data.remote.model.tv

import com.google.gson.annotations.SerializedName

data class RemoteTvShow(
    val id: Int,
    val name: String,
    @SerializedName("poster_path")
    val posterPath: String,
)