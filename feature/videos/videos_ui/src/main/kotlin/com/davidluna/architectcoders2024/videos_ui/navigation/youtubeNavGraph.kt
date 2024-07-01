package com.davidluna.architectcoders2024.videos_ui.navigation

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.davidluna.architectcoders2024.navigation.domain.YoutubeNavigation
import com.davidluna.architectcoders2024.videos_ui.presenter.VideoPlayerViewModel
import com.davidluna.architectcoders2024.videos_ui.view.VideoPlayerScreen

fun NavGraphBuilder.youtubeNavGraph(
    navigateUp: () -> Unit
) {
    navigation<YoutubeNavigation.Init>(
        startDestination = YoutubeNavigation.Video()
    ) {

        composable<YoutubeNavigation.Video> {
            val viewModel: VideoPlayerViewModel = hiltViewModel()
            val state by viewModel.state.collectAsState()
            VideoPlayerScreen(
                state = state,
                navigateUp = { navigateUp() }
            )
        }

    }
}