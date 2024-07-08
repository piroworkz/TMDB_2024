package com.davidluna.architectcoders2024.auth_ui.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.davidluna.architectcoders2024.auth_ui.presenter.LoginEvent
import com.davidluna.architectcoders2024.auth_ui.presenter.LoginViewModel
import com.davidluna.architectcoders2024.auth_ui.view.LoginScreen
import com.davidluna.architectcoders2024.auth_ui.view.composables.IntentView
import com.davidluna.architectcoders2024.navigation.domain.AuthNav
import com.davidluna.architectcoders2024.navigation.domain.Destination

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

            if (state.intent) {
                IntentView(state.token)
            }

            LoginScreen(state = state) { viewModel.sendEvent(it) }
        }

    }
}
