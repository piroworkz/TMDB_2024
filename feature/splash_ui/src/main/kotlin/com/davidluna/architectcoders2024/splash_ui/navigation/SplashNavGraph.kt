package com.davidluna.architectcoders2024.splash_ui.navigation

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.davidluna.architectcoders2024.navigation.domain.Destination
import com.davidluna.architectcoders2024.navigation.domain.StartNav
import com.davidluna.architectcoders2024.splash_ui.presenter.SplashViewModel

fun NavGraphBuilder.splashNavGraph(
    navigateTo: (Destination) -> Unit
) {
    navigation<StartNav.Init>(
        startDestination = StartNav.Splash(true),
    ) {

        composable<StartNav.Splash> {
            val viewModel: SplashViewModel = hiltViewModel()
            val state by viewModel.state.collectAsState()
            state.destination?.let { destination ->
                navigateTo(destination)
            }
            com.davidluna.architectcoders2024.splash_ui.view.SplashScreen(state) {
                viewModel.sendEvent(
                    it
                )
            }
        }
    }
}
