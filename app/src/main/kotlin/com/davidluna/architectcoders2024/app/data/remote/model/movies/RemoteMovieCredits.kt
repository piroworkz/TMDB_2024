package com.davidluna.architectcoders2024.app.data.remote.model.movies

data class RemoteMovieCredits(
    val cast: List<RemoteCast>,
    val crew: List<RemoteCrew>,
    val id: Int
)