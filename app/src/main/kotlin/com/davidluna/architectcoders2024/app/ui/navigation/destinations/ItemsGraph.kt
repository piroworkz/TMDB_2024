package com.davidluna.architectcoders2024.app.ui.navigation.destinations

import kotlinx.serialization.Serializable

@Serializable
sealed interface MoviesGraph : Destination {
    @Serializable
    data object Init : MoviesGraph

    @Serializable
    data class Movies(
        val isTopLevel: Boolean = true
    ) : MoviesGraph

    @Serializable
    data class Detail(val movieId: Int) : MoviesGraph

    @Serializable
    data class VideoPlayer(val movieId: Int) : MoviesGraph
}