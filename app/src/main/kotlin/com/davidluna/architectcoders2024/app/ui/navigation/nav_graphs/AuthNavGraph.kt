package com.davidluna.architectcoders2024.app.ui.navigation.nav_graphs

import android.content.Context
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.davidluna.architectcoders2024.app.app
import com.davidluna.architectcoders2024.app.data.remote.services.authentication.AuthenticationService
import com.davidluna.architectcoders2024.app.data.repositories.AuthenticationRepository
import com.davidluna.architectcoders2024.app.data.repositories.SessionRepository
import com.davidluna.architectcoders2024.app.ui.navigation.destinations.AuthNav
import com.davidluna.architectcoders2024.app.ui.navigation.destinations.Destination
import com.davidluna.architectcoders2024.app.ui.navigation.destinations.MoviesGraph
import com.davidluna.architectcoders2024.app.ui.screens.login.LoginEvent
import com.davidluna.architectcoders2024.app.ui.screens.login.LoginScreen
import com.davidluna.architectcoders2024.app.ui.screens.login.LoginViewModel

fun NavGraphBuilder.authNavGraph(
    navigateTo: (Destination) -> Unit
) {

    navigation<AuthNav.Init>(
        startDestination = AuthNav.Login(true),
    ) {

        composable<AuthNav.Login>(
            deepLinks = listOf(AuthNav.Login.link)
        ) { entry: NavBackStackEntry ->
            val args = entry.arguments?.getString(AuthNav.Login.NAME) ?: ""
            val context = LocalContext.current
            val viewModel: LoginViewModel = viewModel { context.createVM(args) }
            val state by viewModel.state.collectAsState()
            if (state.isLoggedIn) {
                navigateTo(MoviesGraph.Movies())
                viewModel.sendEvent(LoginEvent.IsLoggedIn)
            }
            LoginScreen(state = state, sendEvent = viewModel::sendEvent)
        }

    }
}


private fun Context.createVM(
    args: String,
): LoginViewModel {
    val sessionRepository = SessionRepository(app.sessionDatastore)
    val authenticationRepository =
        AuthenticationRepository(app.client.create(AuthenticationService::class.java))
    return LoginViewModel(
        args = args,
        repository = authenticationRepository,
        local = sessionRepository
    )
}




