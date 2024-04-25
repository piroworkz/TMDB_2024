package com.davidluna.architectcoders2024.app.ui.navigation.nav_graphs

import android.content.Context
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import com.davidluna.architectcoders2024.app.app
import com.davidluna.architectcoders2024.app.ui.navigation.destinations.Destination
import com.davidluna.architectcoders2024.app.ui.navigation.destinations.InitGraph
import com.davidluna.architectcoders2024.app.ui.navigation.route
import com.davidluna.architectcoders2024.app.ui.navigation.setDestinationComposable
import com.davidluna.architectcoders2024.app.ui.screens.splash.AnimationState
import com.davidluna.architectcoders2024.app.ui.screens.splash.SplashScreen
import com.davidluna.architectcoders2024.app.ui.screens.splash.SplashViewModel
import com.davidluna.architectcoders2024.app.data.repositories.SessionRepository

fun NavGraphBuilder.splashNavGraph(
    navigateTo: (Destination) -> Unit
) {
    navigation(
        route = InitGraph.Init.route(),
        startDestination = InitGraph.Splash.route(),
    ) {

        setDestinationComposable(InitGraph.Splash) {
            val context = LocalContext.current
            val viewModel: SplashViewModel = viewModel { context.createSplashVM() }
            val state by viewModel.state.collectAsState()

            if (state.isGranted) {
                state.destination?.let { destination ->
                    navigateTo(destination)
                }
            }
            SplashScreen { viewModel.setPermissionState() }
        }
    }
}

private fun Context.createSplashVM(): SplashViewModel {
    val sessionRepository = SessionRepository(app.sessionDatastore)
    return SplashViewModel(local = sessionRepository)
}