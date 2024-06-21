package com.davidluna.architectcoders2024.app.ui.navigation.nav_graphs

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.davidluna.architectcoders2024.app.ui.navigation.destinations.Destination
import com.davidluna.architectcoders2024.app.ui.navigation.destinations.MoviesNavigation
import com.davidluna.architectcoders2024.app.ui.screens.movies.detail.MovieDetailEvent
import com.davidluna.architectcoders2024.app.ui.screens.movies.detail.MovieDetailScreen
import com.davidluna.architectcoders2024.app.ui.screens.movies.detail.MovieDetailViewModel
import com.davidluna.architectcoders2024.app.ui.screens.movies.master.MoviesEvent
import com.davidluna.architectcoders2024.app.ui.screens.movies.master.MoviesScreen
import com.davidluna.architectcoders2024.app.ui.screens.movies.master.MoviesViewModel
import com.davidluna.architectcoders2024.app.ui.screens.player.VideoPlayerScreen
import com.davidluna.architectcoders2024.app.ui.screens.player.VideoPlayerViewModel
import com.davidluna.architectcoders2024.domain.ContentKind

fun NavGraphBuilder.moviesNavGraph(
    contentKind: ContentKind,
    navigateTo: (Destination) -> Unit,
    navigateUp: () -> Unit
) {
    navigation<MoviesNavigation.Init>(
        startDestination = MoviesNavigation.Movies()
    ) {

        composable<MoviesNavigation.Movies> {
            val viewModel: MoviesViewModel = hiltViewModel()
            val state by viewModel.state.collectAsState()
            SetContentKind(contentKind = contentKind) {
                viewModel.sendEvent(MoviesEvent.SetContentKind(contentKind))
            }
            state.destination?.let {
                navigateTo(it)
                viewModel.sendEvent(MoviesEvent.OnMovieClicked(null))
            }
            MoviesScreen(
                state = state,
                sendEvent = { event: MoviesEvent -> viewModel.sendEvent(event) }
            )
        }

        composable<MoviesNavigation.Detail> {
            val viewModel: MovieDetailViewModel = hiltViewModel()
            val state by viewModel.state.collectAsState()
            SetContentKind(contentKind = contentKind) {
                viewModel.sendEvent(MovieDetailEvent.SetContentKind(contentKind))
            }
            state.destination?.let { destination ->
                navigateTo(destination)
                viewModel.sendEvent(MovieDetailEvent.OnNavigate(null))
            }

            MovieDetailScreen(
                state = state,
                sendEvent = { event: MovieDetailEvent -> viewModel.sendEvent(event) }
            )
        }

        composable<MoviesNavigation.VideoPlayer> {
            val viewModel: VideoPlayerViewModel = hiltViewModel()
            val state by viewModel.state.collectAsState()
            SetContentKind(contentKind = contentKind) {
                viewModel.setContentKind(contentKind)
            }

            VideoPlayerScreen(state, navigateUp)
        }

    }
}

@Composable
private fun SetContentKind(
    contentKind: ContentKind,
    setter: () -> Unit
) {
    LaunchedEffect(key1 = contentKind) {
        setter()
    }
}
