package com.davidluna.tmdb.app.main_ui.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.Movie
import androidx.compose.material.icons.outlined.Tv
import androidx.compose.ui.graphics.vector.ImageVector
import com.davidluna.tmdb.core_ui.R
import com.davidluna.tmdb.media_domain.entities.Catalog

sealed class DrawerItem(
    val titleResource: Int,
    val iconResource: ImageVector,
    val startEndpoint: Catalog?,
) {

    companion object {
        val list = listOf(Movies, TvShows, CloseSession)
    }

    data object Movies : DrawerItem(
        titleResource = R.string.drawer_movies,
        iconResource = Icons.Outlined.Movie,
        startEndpoint = Catalog.MOVIE_NOW_PLAYING
    )

    data object TvShows : DrawerItem(
        titleResource = R.string.drawer_tv_shows,
        iconResource = Icons.Outlined.Tv,
        startEndpoint = Catalog.TV_AIRING_TODAY
    )

    data object CloseSession : DrawerItem(
        titleResource = R.string.drawer_close_session,
        iconResource = Icons.Outlined.Close,
        startEndpoint = null
    )

}