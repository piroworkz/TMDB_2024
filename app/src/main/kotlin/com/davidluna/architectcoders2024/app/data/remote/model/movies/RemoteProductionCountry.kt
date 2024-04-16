package com.davidluna.architectcoders2024.app.data.remote.model.movies

import com.google.gson.annotations.SerializedName

data class RemoteProductionCountry(
    @SerializedName("iso_3166_1")
    val iso31661: String,
    val name: String
)