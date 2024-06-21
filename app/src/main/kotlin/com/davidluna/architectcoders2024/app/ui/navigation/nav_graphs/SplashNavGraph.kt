package com.davidluna.architectcoders2024.app.ui.navigation.nav_graphs

import android.content.Context
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.davidluna.architectcoders2024.app.app
import com.davidluna.architectcoders2024.app.data.repositories.SessionRepository
import com.davidluna.architectcoders2024.app.ui.navigation.destinations.Destination
import com.davidluna.architectcoders2024.app.ui.navigation.destinations.StartNav
import com.davidluna.architectcoders2024.app.ui.screens.splash.SplashScreen
import com.davidluna.architectcoders2024.app.ui.screens.splash.SplashViewModel

fun NavGraphBuilder.splashNavGraph(
    navigateTo: (Destination) -> Unit
) {
    navigation<StartNav.Init>(
        startDestination = StartNav.Splash(true),
    ) {

        composable<StartNav.Splash> {
            val context = LocalContext.current
            val viewModel: SplashViewModel = viewModel { context.createSplashVM() }
            val state by viewModel.state.collectAsState()
            state.destination?.let { destination ->
                navigateTo(destination)
            }
            SplashScreen(state) { viewModel.sendEvent(it) }
        }
    }
}

private fun Context.createSplashVM(): SplashViewModel {
    val sessionRepository = SessionRepository(app.sessionDatastore)
    return SplashViewModel(local = sessionRepository)
}