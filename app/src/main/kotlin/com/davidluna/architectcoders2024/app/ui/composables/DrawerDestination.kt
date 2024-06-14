package com.davidluna.architectcoders2024.app.ui.composables

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.Movie
import androidx.compose.material.icons.outlined.Tv
import androidx.compose.ui.graphics.vector.ImageVector
import com.davidluna.architectcoders2024.R
import com.davidluna.architectcoders2024.app.ui.navigation.destinations.Destination
import com.davidluna.architectcoders2024.app.ui.navigation.destinations.ItemsGraph
import com.davidluna.architectcoders2024.app.ui.navigation.safe_args.ContentType.MOVIE
import com.davidluna.architectcoders2024.app.ui.navigation.safe_args.ContentType.TV

sealed class DrawerDestination(
    val titleResource: Int,
    val iconResource: ImageVector,
    val destination: Destination? = null
) {

    companion object {
        val items = listOf(Movies, TvShows, CloseSession)
    }

    data object Movies : DrawerDestination(
        titleResource = R.string.drawer_movies,
        iconResource = Icons.Outlined.Movie,
        destination = ItemsGraph.Home(contentType = MOVIE)
    )

    data object TvShows : DrawerDestination(
        titleResource = R.string.drawer_tv_shows,
        iconResource = Icons.Outlined.Tv,
        destination = ItemsGraph.Home(contentType = TV)
    )

    data object CloseSession : DrawerDestination(
        titleResource = R.string.drawer_close_session,
        iconResource = Icons.Outlined.Close
    )

}