package com.davidluna.architectcoders2024.media_ui.navigation

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.davidluna.architectcoders2024.core_ui.navigation.destination.Destination
import com.davidluna.architectcoders2024.core_ui.navigation.destination.MediaNavigation
import com.davidluna.architectcoders2024.media_ui.presenter.detail.MovieDetailEvent
import com.davidluna.architectcoders2024.media_ui.presenter.detail.MovieDetailViewModel
import com.davidluna.architectcoders2024.media_ui.presenter.media.MediaCatalogViewModel
import com.davidluna.architectcoders2024.media_ui.presenter.media.MediaEvent
import com.davidluna.architectcoders2024.media_ui.view.details.MediaDetailScreen
import com.davidluna.architectcoders2024.media_ui.view.media.MediaCatalogScreen

fun NavGraphBuilder.mediaNavGraph(
    navigateTo: (Destination) -> Unit,
) {
    navigation<MediaNavigation.Init>(
        startDestination = MediaNavigation.MediaCatalog()
    ) {

        composable<MediaNavigation.MediaCatalog> {
            val viewModel: MediaCatalogViewModel = hiltViewModel()
            val state by viewModel.state.collectAsState()
            state.destination?.let {
                navigateTo(it)
                viewModel.sendEvent(MediaEvent.OnMovieClicked(null))
            }
            MediaCatalogScreen(
                state = state,
                sendEvent = { event: MediaEvent -> viewModel.sendEvent(event) }
            )
        }

        composable<MediaNavigation.Detail> {
            val viewModel: MovieDetailViewModel = hiltViewModel()
            val state by viewModel.state.collectAsState()
            state.destination?.let { destination ->
                navigateTo(destination)
                viewModel.sendEvent(MovieDetailEvent.OnNavigate(null))
            }
            MediaDetailScreen(
                state = state,
                sendEvent = { event: MovieDetailEvent -> viewModel.sendEvent(event) }
            )
        }

    }
}

