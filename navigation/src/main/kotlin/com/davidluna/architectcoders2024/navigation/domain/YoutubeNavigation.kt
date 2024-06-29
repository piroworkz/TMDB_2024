package com.davidluna.architectcoders2024.navigation.domain

import kotlinx.serialization.Serializable

@Serializable
sealed interface YoutubeNavigation : Destination {

    @Serializable
    data object Init : YoutubeNavigation

    @Serializable
    data class Video(
        val movieId: Int = 0,
        val hideAppBar: Boolean = true
    ) : YoutubeNavigation
}