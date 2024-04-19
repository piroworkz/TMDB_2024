package com.davidluna.architectcoders2024.app.ui.navigation.nav_graphs

import android.content.Context
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import com.davidluna.architectcoders2024.app.app
import com.davidluna.architectcoders2024.app.ui.navigation.destinations.Destination
import com.davidluna.architectcoders2024.app.ui.navigation.destinations.MainGraph
import com.davidluna.architectcoders2024.app.ui.navigation.navComposable
import com.davidluna.architectcoders2024.app.ui.navigation.route
import com.davidluna.architectcoders2024.app.ui.screens.master.MoviesEvent
import com.davidluna.architectcoders2024.data.MoviesRepository
import com.davidluna.architectcoders2024.app.ui.screens.master.MoviesScreen
import com.davidluna.architectcoders2024.app.ui.screens.master.MoviesViewModel

fun NavGraphBuilder.mainNavGraph(
    navigateTo: (Destination) -> Unit
) {
    navigation(
        route = MainGraph.Init.route(),
        startDestination = MainGraph.Home.route()
    ) {

        navComposable(MainGraph.Home) {
            val context = LocalContext.current
            val viewModel: MoviesViewModel = viewModel { context.buildMoviesViewModel() }
            val state by viewModel.state.collectAsState()
            state.selectedMovieId?.let {
                navigateTo(MainGraph.Detail(it))
                viewModel.sendEvent(MoviesEvent.OnMovieClicked(null))
            }
            MoviesScreen(
                state = state,
                sendEvent = viewModel::sendEvent
            )
        }

        navComposable(MainGraph.Detail()) {
            Text(text = "Detail")
        }

    }
}

private fun Context.buildMoviesViewModel(): MoviesViewModel {
    val service = app.client.moviesService
    val repository = MoviesRepository(service)
    return MoviesViewModel(repository)
}
