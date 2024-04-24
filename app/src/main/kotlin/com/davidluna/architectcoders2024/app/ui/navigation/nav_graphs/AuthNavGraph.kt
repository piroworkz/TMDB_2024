package com.davidluna.architectcoders2024.app.ui.navigation.nav_graphs

import android.content.Context
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import com.davidluna.architectcoders2024.app.app
import com.davidluna.architectcoders2024.app.data.remote.services.authentication.AuthenticationService
import com.davidluna.architectcoders2024.app.ui.navigation.destinations.AuthGraph
import com.davidluna.architectcoders2024.app.ui.navigation.destinations.Destination
import com.davidluna.architectcoders2024.app.ui.navigation.destinations.MoviesGraph
import com.davidluna.architectcoders2024.app.ui.navigation.route
import com.davidluna.architectcoders2024.app.ui.navigation.safe_args.DefaultArgs
import com.davidluna.architectcoders2024.app.ui.navigation.setDestinationComposable
import com.davidluna.architectcoders2024.app.ui.screens.login.LoginEvent
import com.davidluna.architectcoders2024.app.ui.screens.login.LoginScreen
import com.davidluna.architectcoders2024.app.ui.screens.login.LoginViewModel
import com.davidluna.architectcoders2024.app.data.repositories.AuthenticationRepository
import com.davidluna.architectcoders2024.app.data.repositories.SessionRepository

fun NavGraphBuilder.authNavGraph(
    navigateTo: (Destination) -> Unit
) {

    navigation(
        route = AuthGraph.Init.route(),
        startDestination = AuthGraph.Login.route(),
    ) {

        setDestinationComposable(
            destination = AuthGraph.Login
        ) { entry: NavBackStackEntry ->
            val args = entry.arguments?.getString(DefaultArgs.Auth.name) ?: ""
            val context = LocalContext.current
            val viewModel: LoginViewModel = viewModel { context.createVM(args) }
            val state by viewModel.state.collectAsState()
            if (state.isLoggedIn) {
                navigateTo(MoviesGraph.Home)
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




