package com.davidluna.architectcoders2024.app.ui.navigation.destinations

import kotlinx.serialization.Serializable

@Serializable
sealed interface MoviesNavigation : Destination {
    @Serializable
    data object Init : MoviesNavigation

    @Serializable
    data class Movies(
        val isTopLevel: Boolean = true
    ) : MoviesNavigation

    @Serializable
    data class Detail(
        val movieId: Int
    ) : MoviesNavigation

    @Serializable
    data class VideoPlayer(
        val movieId: Int
    ) : MoviesNavigation
}
