package com.davidluna.tmdb.videos_ui.navigation

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.navigation.toRoute
import com.davidluna.tmdb.core_ui.navigation.destination.YoutubeNavigation
import com.davidluna.tmdb.videos_ui.presenter.VideoPlayerViewModel
import com.davidluna.tmdb.videos_ui.view.VideoPlayerScreen
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

fun NavGraphBuilder.youtubeNavGraph(
    navigateUp: () -> Unit,
) {
    navigation<YoutubeNavigation.Init>(
        startDestination = YoutubeNavigation.Video()
    ) {

        composable<YoutubeNavigation.Video> {
            val viewModel =
                koinViewModel<VideoPlayerViewModel> { parametersOf(it.toRoute<YoutubeNavigation.Video>().mediaId) }
            val state by viewModel.state.collectAsState()
            VideoPlayerScreen(
                state = state,
                navigateUp = { navigateUp() }
            )
        }

    }
}