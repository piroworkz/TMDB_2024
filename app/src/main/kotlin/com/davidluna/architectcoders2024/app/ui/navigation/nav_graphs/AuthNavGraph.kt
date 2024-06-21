package com.davidluna.architectcoders2024.app.ui.navigation.nav_graphs

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.davidluna.architectcoders2024.app.ui.navigation.destinations.AuthNav
import com.davidluna.architectcoders2024.app.ui.navigation.destinations.Destination
import com.davidluna.architectcoders2024.app.ui.screens.login.LoginEvent
import com.davidluna.architectcoders2024.app.ui.screens.login.LoginScreen
import com.davidluna.architectcoders2024.app.ui.screens.login.LoginViewModel

fun NavGraphBuilder.authNavGraph(
    navigateTo: (Destination) -> Unit
) {

    navigation<AuthNav.Init>(
        startDestination = AuthNav.Login(),
    ) {

        composable<AuthNav.Login>(
            deepLinks = listOf(AuthNav.Login.link)
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
