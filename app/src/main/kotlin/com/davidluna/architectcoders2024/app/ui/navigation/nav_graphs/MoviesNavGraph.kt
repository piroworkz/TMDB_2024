package com.davidluna.architectcoders2024.app.ui.navigation.nav_graphs

import android.content.Context
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import com.davidluna.architectcoders2024.app.app
import com.davidluna.architectcoders2024.app.ui.navigation.destinations.Destination
import com.davidluna.architectcoders2024.app.ui.navigation.destinations.MainGraph
import com.davidluna.architectcoders2024.app.ui.navigation.route
import com.davidluna.architectcoders2024.app.ui.navigation.safe_args.Args
import com.davidluna.architectcoders2024.app.ui.navigation.setDestinationComposable
import com.davidluna.architectcoders2024.app.ui.navigation.setDestinationDialog
import com.davidluna.architectcoders2024.app.ui.screens.detail.MovieDetailEvent
import com.davidluna.architectcoders2024.app.ui.screens.detail.MovieDetailScreen
import com.davidluna.architectcoders2024.app.ui.screens.detail.MovieDetailViewModel
import com.davidluna.architectcoders2024.app.ui.screens.master.MoviesEvent
import com.davidluna.architectcoders2024.app.ui.screens.master.MoviesScreen
import com.davidluna.architectcoders2024.app.ui.screens.master.MoviesViewModel
import com.davidluna.architectcoders2024.app.ui.screens.player.VideoPlayerScreen
import com.davidluna.architectcoders2024.app.utils.log
import com.davidluna.architectcoders2024.data.MovieDetailsRepository
import com.davidluna.architectcoders2024.data.MoviesRepository

fun NavGraphBuilder.moviesNavGraph(
    navigateTo: (Destination) -> Unit
) {
    navigation(
        route = MainGraph.Init.route(),
        startDestination = MainGraph.Home.route()
    ) {

        setDestinationComposable(MainGraph.Home) {
            val context = LocalContext.current
            val viewModel: MoviesViewModel = viewModel { context.buildMoviesViewModel() }
            val state by viewModel.state.collectAsState()

            state.selectedMovieId?.let {
                navigateTo(MainGraph.Detail(it))
                viewModel.sendEvent(MoviesEvent.OnMovieClicked(null))
            }
            MoviesScreen(
                state = state,
                sendEvent = { event: MoviesEvent -> viewModel.sendEvent(event) }
            )
        }

        setDestinationComposable(MainGraph.Detail()) {
            val movieId = it.arguments?.getInt(Args.DetailId.name)
            val context = LocalContext.current
            val viewModel = viewModel { context.buildMovieDetailViewModel(movieId) }
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

        setDestinationDialog(MainGraph.VideoPlayer()) {
            val movieId = it.arguments?.getInt(Args.DetailId.name)
            movieId?.toString()?.log("videoId")
            VideoPlayerScreen(videoId = "Way9Dexny3w")
        }

    }
}

private fun Context.buildMovieDetailViewModel(
    movieId: Int?
): MovieDetailViewModel {
    val repository = MovieDetailsRepository(app.client.movieDetailService)
    return MovieDetailViewModel(movieId, repository)
}

private fun Context.buildMoviesViewModel(): MoviesViewModel {
    val repository = MoviesRepository(app.client.moviesService)
    return MoviesViewModel(repository)
}
