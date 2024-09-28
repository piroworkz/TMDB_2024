package com.davidluna.tmdb.core_ui.navigation.destination

import kotlinx.serialization.Serializable

@Serializable
sealed interface YoutubeNavigation : Destination {

    @Serializable
    data object Init : YoutubeNavigation

    @Serializable
    data class Video(
        val hideAppBar: Boolean = true,
        val mediaId: Int = -1,
    ) : MediaNavigation
}