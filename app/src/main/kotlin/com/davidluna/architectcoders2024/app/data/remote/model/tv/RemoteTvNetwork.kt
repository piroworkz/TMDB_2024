package com.davidluna.architectcoders2024.app.data.remote.model.tv

import com.google.gson.annotations.SerializedName

data class RemoteTvNetwork(
    val id: Int,
    @SerializedName("logo_path")
    val logoPath: String,
    val name: String,
    @SerializedName("origin_country")
    val originCountry: String
)