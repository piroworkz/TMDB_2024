package com.davidluna.architectcoders2024.app.data.remote.model.movies

data class RemoteImages(
    val backdrops: List<RemoteImage>,
    val id: Int,
    val logos: List<RemoteImage>,
    val posters: List<RemoteImage>
)