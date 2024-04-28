package com.davidluna.architectcoders2024.app.ui.navigation.nav_graphs

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import com.davidluna.architectcoders2024.app.ui.navigation.destinations.Destination
import com.davidluna.architectcoders2024.app.ui.navigation.destinations.MoviesGraph
import com.davidluna.architectcoders2024.app.ui.navigation.route
import com.davidluna.architectcoders2024.app.ui.navigation.setDestinationComposable
import com.davidluna.architectcoders2024.app.ui.screens.movies.detail.MovieDetailEvent
import com.davidluna.architectcoders2024.app.ui.screens.movies.detail.MovieDetailScreen
import com.davidluna.architectcoders2024.app.ui.screens.movies.detail.MovieDetailViewModel
import com.davidluna.architectcoders2024.app.ui.screens.movies.master.MoviesEvent
import com.davidluna.architectcoders2024.app.ui.screens.movies.master.MoviesScreen
import com.davidluna.architectcoders2024.app.ui.screens.movies.master.MoviesViewModel
import com.davidluna.architectcoders2024.app.ui.screens.player.VideoPlayerScreen
import com.davidluna.architectcoders2024.app.ui.screens.player.VideoPlayerViewModel

fun NavGraphBuilder.moviesNavGraph(
    navigateTo: (Destination) -> Unit,
    navigateUp: () -> Unit
) {
    navigation(
        route = MoviesGraph.Init.route(),
        startDestination = MoviesGraph.Home.route()
    ) {

        setDestinationComposable(MoviesGraph.Home) {
            val viewModel: MoviesViewModel = hiltViewModel()
            val state by viewModel.state.collectAsState()

            state.destination?.let { destination ->
                navigateTo(destination)
                viewModel.sendEvent(MoviesEvent.OnMovieClicked(null))
            }
            MoviesScreen(
                state = state
            ) { viewModel.sendEvent(it) }
        }

        setDestinationComposable(MoviesGraph.Detail()) {
            val viewModel: MovieDetailViewModel = hiltViewModel()
            val state by viewModel.state.collectAsState()

            state.destination?.let { destination ->
                navigateTo(destination)
                viewModel.sendEvent(MovieDetailEvent.OnNavigate(null))
            }

            MovieDetailScreen(
                state = state,
                sendEvent = { event: MovieDetailEvent -> viewModel.sendEvent(event) }
            )
        }

        setDestinationComposable(MoviesGraph.VideoPlayer()) {
            val viewModel: VideoPlayerViewModel = hiltViewModel()
            val state by viewModel.state.collectAsState()
            VideoPlayerScreen(state) { navigateUp() }
        }

    }
}
