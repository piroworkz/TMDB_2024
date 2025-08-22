package com.davidluna.tmdb.media_ui.navigation

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.davidluna.tmdb.core_ui.navigation.Destination
import com.davidluna.tmdb.media_ui.view.details.MediaDetailScreen
import com.davidluna.tmdb.media_ui.view.media.MediaCatalogScreen
import com.davidluna.tmdb.media_ui.view.videos.VideoPlayerScreen

@OptIn(ExperimentalSharedTransitionApi::class)
fun NavGraphBuilder.mediaNavGraph(
    navigateTo: (Destination) -> Unit,
) {
    navigation<MediaNavigation.Init>(
        startDestination = MediaNavigation.MediaCatalog()
    ) {
        composable<MediaNavigation.MediaCatalog> { MediaCatalogScreen(navigateTo = { navigateTo(it) }) }
        composable<MediaNavigation.Detail> { MediaDetailScreen { navigateTo(it) } }
        composable<MediaNavigation.Video> { VideoPlayerScreen() }
    }
}