package com.davidluna.architectcoders2024.app.ui.navigation.nav_graphs

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import com.davidluna.architectcoders2024.app.ui.navigation.destinations.Destination
import com.davidluna.architectcoders2024.app.ui.navigation.destinations.InitGraph
import com.davidluna.architectcoders2024.app.ui.navigation.route
import com.davidluna.architectcoders2024.app.ui.navigation.setDestinationComposable
import com.davidluna.architectcoders2024.app.ui.screens.splash.SplashScreen
import com.davidluna.architectcoders2024.app.ui.screens.splash.SplashViewModel

fun NavGraphBuilder.splashNavGraph(
    navigateTo: (Destination) -> Unit
) {
    navigation(
        route = InitGraph.Init.route(),
        startDestination = InitGraph.Splash.route(),
    ) {

        setDestinationComposable(InitGraph.Splash) {
            val viewModel: SplashViewModel = hiltViewModel()
            val state by viewModel.state.collectAsState()
            state.destination?.let { destination ->
                navigateTo(destination)
            }
            SplashScreen(state) { viewModel.sendEvent(it) }
        }
    }
}
