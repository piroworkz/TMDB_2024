package com.davidluna.architectcoders2024.app.data.remote.model.movies

import com.google.gson.annotations.SerializedName

data class RemoteReview(
    val author: String,
    @SerializedName("author_details")
    val authorDetails: RemoteAuthorDetails,
    val content: String,
    @SerializedName("created_at")
    val createdAt: String,
    val id: String,
    @SerializedName("updated_at")
    val updatedAt: String,
    val url: String
)