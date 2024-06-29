package com.davidluna.architectcoders2024.media_ui.navigation

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.davidluna.architectcoders2024.media_ui.presenter.detail.MovieDetailEvent
import com.davidluna.architectcoders2024.media_ui.presenter.detail.MovieDetailViewModel
import com.davidluna.architectcoders2024.media_ui.view.details.MovieDetailScreen
import com.davidluna.architectcoders2024.media_ui.presenter.media.MoviesEvent
import com.davidluna.architectcoders2024.media_ui.view.media.MoviesScreen
import com.davidluna.architectcoders2024.media_ui.presenter.media.MoviesViewModel
import com.davidluna.architectcoders2024.navigation.domain.Destination
import com.davidluna.architectcoders2024.navigation.domain.MoviesNavigation

fun NavGraphBuilder.moviesNavGraph(
    navigateTo: (Destination) -> Unit,
) {
    navigation<MoviesNavigation.Init>(
        startDestination = MoviesNavigation.Movies()
    ) {

        composable<MoviesNavigation.Movies> {
            val viewModel: MoviesViewModel = hiltViewModel()
            val state by viewModel.state.collectAsState()
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
            state.destination?.let { destination ->
                navigateTo(destination)
                viewModel.sendEvent(MovieDetailEvent.OnNavigate(null))
            }
            MovieDetailScreen(
                state = state,
                sendEvent = { event: MovieDetailEvent -> viewModel.sendEvent(event) }
            )
        }

    }
}

