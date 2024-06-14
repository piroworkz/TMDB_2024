package com.davidluna.architectcoders2024.app.ui.navigation.nav_graphs

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import com.davidluna.architectcoders2024.app.ui.navigation.destinations.Destination
import com.davidluna.architectcoders2024.app.ui.navigation.destinations.ItemsGraph
import com.davidluna.architectcoders2024.app.ui.navigation.route
import com.davidluna.architectcoders2024.app.ui.navigation.setDestinationComposable
import com.davidluna.architectcoders2024.app.ui.screens.content.detail.ItemDetailEvent
import com.davidluna.architectcoders2024.app.ui.screens.content.detail.ItemDetailScreen
import com.davidluna.architectcoders2024.app.ui.screens.content.detail.ItemDetailViewModel
import com.davidluna.architectcoders2024.app.ui.screens.content.master.MainContentEvent
import com.davidluna.architectcoders2024.app.ui.screens.content.master.MainContentScreen
import com.davidluna.architectcoders2024.app.ui.screens.content.master.MainContentViewModel
import com.davidluna.architectcoders2024.app.ui.screens.player.VideoPlayerScreen
import com.davidluna.architectcoders2024.app.ui.screens.player.VideoPlayerViewModel

fun NavGraphBuilder.itemsNavGraph(
    navigateTo: (Destination) -> Unit,
    navigateUp: () -> Unit
) {
    navigation(
        route = ItemsGraph.Init.route(),
        startDestination = ItemsGraph.Home().route()
    ) {

        setDestinationComposable(ItemsGraph.Home()) {
            val viewModel: MainContentViewModel = hiltViewModel()
            val state by viewModel.state.collectAsState()

            state.destination?.let { destination ->
                navigateTo(destination)
                viewModel.sendEvent(MainContentEvent.OnMovieClicked(null))
            }
            MainContentScreen(
                state = state
            ) { viewModel.sendEvent(it) }
        }

        setDestinationComposable(ItemsGraph.Detail()) {
            val viewModel: ItemDetailViewModel = hiltViewModel()
            val state by viewModel.state.collectAsState()

            state.destination?.let { destination ->
                navigateTo(destination)
                viewModel.sendEvent(ItemDetailEvent.OnNavigate(null))
            }

            ItemDetailScreen(
                state = state,
                sendEvent = { event: ItemDetailEvent -> viewModel.sendEvent(event) }
            )
        }

        setDestinationComposable(ItemsGraph.VideoPlayer()) {
            val viewModel: VideoPlayerViewModel = hiltViewModel()
            val state by viewModel.state.collectAsState()
            VideoPlayerScreen(state) { navigateUp() }
        }

    }
}
