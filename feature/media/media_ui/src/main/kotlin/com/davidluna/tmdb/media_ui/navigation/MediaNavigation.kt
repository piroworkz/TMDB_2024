package com.davidluna.tmdb.media_ui.navigation

import com.davidluna.tmdb.core_ui.navigation.Destination
import kotlinx.serialization.Serializable

@Serializable
sealed interface MediaNavigation : Destination {

    @Serializable
    data object Init : MediaNavigation

    @Serializable
    data class MediaCatalog(
        val topLevel: Boolean = true,
        val appBarTitle: String = "TMDB 2025",
        val shouldPopSplash: Boolean = false,
    ) : MediaNavigation

    @Serializable
    data class Detail(
        val mediaId: Int = DEFAULT_ID,
        val appBarTitle: String = String(),
        val topLevel: Boolean = false,
    ) : MediaNavigation

    @Serializable
    data class Video(
        val mediaId: Int = DEFAULT_ID,
        val appBarTitle: String = String(),
        val hideAppBar: Boolean = true
    ) : MediaNavigation

    companion object {
        private const val DEFAULT_ID = -1
    }
}