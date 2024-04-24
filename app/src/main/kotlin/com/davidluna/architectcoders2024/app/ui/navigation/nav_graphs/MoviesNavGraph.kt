package com.davidluna.architectcoders2024.app.ui.navigation.nav_graphs

import android.content.Context
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import com.davidluna.architectcoders2024.app.app
import com.davidluna.architectcoders2024.app.data.remote.services.movies.MovieDetailService
import com.davidluna.architectcoders2024.app.data.remote.services.movies.MoviesService
import com.davidluna.architectcoders2024.app.data.repositories.MovieDetailsRepository
import com.davidluna.architectcoders2024.app.data.repositories.MoviesRepository
import com.davidluna.architectcoders2024.app.ui.navigation.destinations.Destination
import com.davidluna.architectcoders2024.app.ui.navigation.destinations.MoviesGraph
import com.davidluna.architectcoders2024.app.ui.navigation.route
import com.davidluna.architectcoders2024.app.ui.navigation.safe_args.Args
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
            val context = LocalContext.current
            val viewModel: MoviesViewModel = viewModel { context.buildMoviesViewModel() }
            val state by viewModel.state.collectAsState()

            state.selectedMovieId?.let {
                navigateTo(MoviesGraph.Detail(it))
                viewModel.sendEvent(MoviesEvent.OnMovieClicked(null))
            }
            MoviesScreen(
                state = state,
                sendEvent = { event: MoviesEvent -> viewModel.sendEvent(event) }
            )
        }

        setDestinationComposable(MoviesGraph.Detail()) {
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

        setDestinationComposable(MoviesGraph.VideoPlayer()) {
            val movieId = it.arguments?.getInt(Args.DetailId.name)
            val context = LocalContext.current
            val viewModel = viewModel { context.buildPlayerViewModel(movieId) }
            val state by viewModel.state.collectAsState()
            VideoPlayerScreen(state, navigateUp)
        }

    }
}

fun Context.buildPlayerViewModel(movieId: Int?): VideoPlayerViewModel {
    val repository = MovieDetailsRepository(app.client.create(MovieDetailService::class.java))
    return VideoPlayerViewModel(repository, movieId)
}

private fun Context.buildMovieDetailViewModel(
    movieId: Int?
): MovieDetailViewModel {
    val repository = MovieDetailsRepository(app.client.create(MovieDetailService::class.java))
    val moviesRepository = MoviesRepository(app.client.create(MoviesService::class.java))
    return MovieDetailViewModel(movieId, repository, moviesRepository)
}

private fun Context.buildMoviesViewModel(): MoviesViewModel {
    val repository = MoviesRepository(app.client.create(MoviesService::class.java))
    return MoviesViewModel(repository)
}
