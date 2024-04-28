package com.davidluna.architectcoders2024.app.ui.navigation.nav_graphs

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import com.davidluna.architectcoders2024.app.ui.navigation.destinations.AuthGraph
import com.davidluna.architectcoders2024.app.ui.navigation.destinations.Destination
import com.davidluna.architectcoders2024.app.ui.navigation.route
import com.davidluna.architectcoders2024.app.ui.navigation.setDestinationComposable
import com.davidluna.architectcoders2024.app.ui.screens.login.LoginEvent
import com.davidluna.architectcoders2024.app.ui.screens.login.LoginScreen
import com.davidluna.architectcoders2024.app.ui.screens.login.LoginViewModel

fun NavGraphBuilder.authNavGraph(
    navigateTo: (Destination) -> Unit
) {

    navigation(
        route = AuthGraph.Init.route(),
        startDestination = AuthGraph.Login.route(),
    ) {

        setDestinationComposable(
            destination = AuthGraph.Login
        ) {
            val viewModel: LoginViewModel = hiltViewModel()
            val state by viewModel.state.collectAsState()

            state.destination?.let {
                navigateTo(it)
                viewModel.sendEvent(LoginEvent.IsLoggedIn(null))
            }
            LoginScreen(state = state) { viewModel.sendEvent(it) }
        }

    }
}
