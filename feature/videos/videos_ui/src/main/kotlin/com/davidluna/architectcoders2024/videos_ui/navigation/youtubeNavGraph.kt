package com.davidluna.architectcoders2024.videos_ui.navigation

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.navigation.toRoute
import com.davidluna.architectcoders2024.core_ui.navigation.destination.YoutubeNavigation
import com.davidluna.architectcoders2024.videos_ui.presenter.VideoPlayerViewModel
import com.davidluna.architectcoders2024.videos_ui.view.VideoPlayerScreen

fun NavGraphBuilder.youtubeNavGraph(
    navigateUp: () -> Unit,
) {
    navigation<YoutubeNavigation.Init>(
        startDestination = YoutubeNavigation.Video()
    ) {

        composable<YoutubeNavigation.Video> { backStackEntry: NavBackStackEntry ->
            val viewModel =
                hiltViewModel<VideoPlayerViewModel, VideoPlayerViewModel.MediaIdFactory> { factory ->
                    factory.create(backStackEntry.toRoute<YoutubeNavigation.Video>().mediaId)
                }
            val state by viewModel.state.collectAsState()
            VideoPlayerScreen(
                state = state,
                navigateUp = { navigateUp() }
            )
        }

    }
}