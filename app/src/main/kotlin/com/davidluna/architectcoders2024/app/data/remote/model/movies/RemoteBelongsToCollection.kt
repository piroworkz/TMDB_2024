package com.davidluna.architectcoders2024.app.data.remote.model.movies

import com.google.gson.annotations.SerializedName

data class RemoteBelongsToCollection(
    @SerializedName("backdrop_path")
    val backdropPath: String,
    val id: Int,
    val name: String,
    @SerializedName("poster_path")
    val posterPath: String
)