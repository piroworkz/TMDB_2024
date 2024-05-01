package com.davidluna.architectcoders2024.app.ui.composables

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.Movie
import androidx.compose.material.icons.outlined.Tv
import androidx.compose.ui.graphics.vector.ImageVector
import com.davidluna.architectcoders2024.R

sealed class DrawerDestinations(
    val titleResource: Int,
    val iconResource: ImageVector
) {

    companion object {
        val items = listOf(Movies, TvShows, CloseSession)
    }

    data object Movies : DrawerDestinations(
        titleResource = R.string.drawer_movies,
        iconResource = Icons.Outlined.Movie
    )

    data object TvShows : DrawerDestinations(
        titleResource = R.string.drawer_tv_shows,
        iconResource = Icons.Outlined.Tv
    )

    data object CloseSession : DrawerDestinations(
        titleResource = R.string.drawer_close_session,
        iconResource = Icons.Outlined.Close
    )

}