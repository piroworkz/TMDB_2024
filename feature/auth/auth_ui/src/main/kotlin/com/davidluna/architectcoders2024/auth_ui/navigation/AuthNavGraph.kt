package com.davidluna.architectcoders2024.auth_ui.navigation

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import com.davidluna.architectcoders2024.auth_ui.presenter.LoginEvent
import com.davidluna.architectcoders2024.auth_ui.presenter.LoginViewModel
import com.davidluna.architectcoders2024.auth_ui.view.LoginScreen
import com.davidluna.architectcoders2024.auth_ui.view.composables.IntentView
import com.davidluna.architectcoders2024.navigation.domain.composable
import com.davidluna.architectcoders2024.navigation.domain.destination.AuthNavigation
import com.davidluna.architectcoders2024.navigation.domain.destination.Destination
import com.davidluna.architectcoders2024.navigation.domain.route

fun NavGraphBuilder.authNavGraph(
    navigateTo: (Destination) -> Unit
) {

    navigation(
        route = AuthNavigation.Init.route(),
        startDestination = AuthNavigation.Login.route(),
    ) {

        composable(AuthNavigation.Login) {
            val viewModel: LoginViewModel = hiltViewModel()
            val state by viewModel.state.collectAsState()
            state.destination?.let {
                navigateTo(it)
                viewModel.sendEvent(LoginEvent.IsLoggedIn(null))
            }
            if (state.launchTMDBWeb) {
                IntentView(state.token)
            }
            LoginScreen(state = state) { viewModel.sendEvent(it) }
        }

    }
}
