package com.davidluna.architectcoders2024.navigation.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.Movie
import androidx.compose.material.icons.outlined.Tv
import androidx.compose.ui.graphics.vector.ImageVector
import com.davidluna.architectcoders2024.core_domain.core_entities.ContentKind
import com.davidluna.architectcoders2024.core_ui.R

sealed class DrawerDestination(
    val titleResource: Int,
    val iconResource: ImageVector,
    val destination: Destination? = null,
    val contentKind: ContentKind? = null
) {

    companion object {
        val items = listOf(Movies, TvShows, CloseSession)
    }

    data object Movies : DrawerDestination(
        titleResource = R.string.drawer_movies,
        iconResource = Icons.Outlined.Movie,
        destination = MoviesNavigation.Movies(),
        contentKind = ContentKind.MOVIE
    )

    data object TvShows : DrawerDestination(
        titleResource = R.string.drawer_tv_shows,
        iconResource = Icons.Outlined.Tv,
        destination = MoviesNavigation.Movies(),
        contentKind = ContentKind.TV_SHOW
    )

    data object CloseSession : DrawerDestination(
        titleResource = R.string.drawer_close_session,
        iconResource = Icons.Outlined.Close
    )

}