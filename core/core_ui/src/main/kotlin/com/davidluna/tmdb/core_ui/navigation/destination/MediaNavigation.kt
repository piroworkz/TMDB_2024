package com.davidluna.tmdb.core_ui.navigation.destination

import kotlinx.serialization.Serializable

@Serializable
sealed interface MediaNavigation : Destination {

    @Serializable
    data object Init : MediaNavigation

    @Serializable
    data class MediaCatalog(val topLevel: Boolean = true, val appBarTitle: String = "TMDB 2025") : MediaNavigation

    @Serializable
    data class Detail(
        val mediaId: Int = DEFAULT_ID,
        val appBarTitle: String = String(),
        val topLevel: Boolean = false,
    ) : MediaNavigation

    companion object {
        const val DEFAULT_ID = -1
    }
}