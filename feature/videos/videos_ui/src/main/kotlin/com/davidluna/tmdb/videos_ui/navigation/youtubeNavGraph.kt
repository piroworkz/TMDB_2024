package com.davidluna.tmdb.videos_ui.navigation

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.davidluna.tmdb.core_ui.navigation.destination.YoutubeNavigation
import com.davidluna.tmdb.videos_ui.presenter.VideoPlayerViewModel
import com.davidluna.tmdb.videos_ui.view.VideoPlayerScreen

fun NavGraphBuilder.youtubeNavGraph(
    navigateUp: () -> Unit,
) {
    navigation<YoutubeNavigation.Init>(
        startDestination = YoutubeNavigation.Video()
    ) {

        composable<YoutubeNavigation.Video> {
            val viewModel = hiltViewModel<VideoPlayerViewModel>()
            val state by viewModel.state.collectAsState()
            VideoPlayerScreen(
                state = state,
                navigateUp = { navigateUp() }
            )
        }

    }
}