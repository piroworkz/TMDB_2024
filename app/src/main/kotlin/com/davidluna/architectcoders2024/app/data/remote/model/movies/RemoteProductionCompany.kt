package com.davidluna.architectcoders2024.app.data.remote.model.movies

import com.google.gson.annotations.SerializedName

data class RemoteProductionCompany(
    val id: Int,
    @SerializedName("logo_path")
    val logoPath: String,
    val name: String,
    @SerializedName("origin_country")
    val originCountry: String
)