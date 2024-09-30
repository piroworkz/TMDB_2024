package com.davidluna.tmdb.splash.navigation

import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.davidluna.tmdb.core_ui.navigation.destination.Destination
import com.davidluna.tmdb.core_ui.navigation.destination.StartNavigation
import com.davidluna.tmdb.splash.presenter.SplashEvent
import com.davidluna.tmdb.splash.presenter.SplashViewModel
import com.davidluna.tmdb.splash.view.SplashScreen
import org.koin.compose.viewmodel.koinViewModel

fun NavGraphBuilder.splashNavGraph(
    navigateTo: (Destination) -> Unit,
) {

    navigation<StartNavigation.Init>(
        startDestination = StartNavigation.Splash()
    ) {
        composable<StartNavigation.Splash> {
            val viewModel: SplashViewModel = koinViewModel()
            val state by viewModel.state.collectAsStateWithLifecycle()

            state.destination?.let {
                navigateTo(it)
                viewModel.onEvent(SplashEvent.NavigateTo(null))
            }

            SplashScreen(
                state = state,
                sendEvent = viewModel::onEvent,
            )
        }
    }
}
