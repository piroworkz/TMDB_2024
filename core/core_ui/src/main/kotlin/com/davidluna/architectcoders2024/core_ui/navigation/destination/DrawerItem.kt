package com.davidluna.architectcoders2024.core_ui.navigation.destination

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.Movie
import androidx.compose.material.icons.outlined.Tv
import androidx.compose.ui.graphics.vector.ImageVector
import com.davidluna.architectcoders2024.core_domain.entities.ContentKind
import com.davidluna.architectcoders2024.core_ui.R

sealed class DrawerItem(
    val titleResource: Int,
    val iconResource: ImageVector,
    val destination: Destination? = null,
    val contentKind: ContentKind? = null
) {

    companion object {
        val items = listOf(Movies, TvShows, CloseSession)
    }

    data object Movies : DrawerItem(
        titleResource = R.string.drawer_movies,
        iconResource = Icons.Outlined.Movie,
        destination = MediaNavigation.MediaCatalog,
        contentKind = ContentKind.MOVIE
    )

    data object TvShows : DrawerItem(
        titleResource = R.string.drawer_tv_shows,
        iconResource = Icons.Outlined.Tv,
        destination = MediaNavigation.MediaCatalog,
        contentKind = ContentKind.TV_SHOW
    )

    data object CloseSession : DrawerItem(
        titleResource = R.string.drawer_close_session,
        iconResource = Icons.Outlined.Close
    )

}