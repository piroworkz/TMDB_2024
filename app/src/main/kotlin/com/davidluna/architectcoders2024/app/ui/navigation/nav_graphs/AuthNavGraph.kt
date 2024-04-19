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
import com.davidluna.architectcoders2024.app.ui.navigation.destinations.AuthGraph
import com.davidluna.architectcoders2024.app.ui.navigation.destinations.Destination
import com.davidluna.architectcoders2024.app.ui.navigation.destinations.MainGraph
import com.davidluna.architectcoders2024.app.ui.navigation.navComposable
import com.davidluna.architectcoders2024.app.ui.navigation.route
import com.davidluna.architectcoders2024.app.ui.navigation.safe_args.Default
import com.davidluna.architectcoders2024.app.ui.screens.login.LoginScreen
import com.davidluna.architectcoders2024.app.ui.screens.login.LoginViewModel
import com.davidluna.architectcoders2024.app.utils.log
import com.davidluna.architectcoders2024.data.AuthenticationRepository
import com.davidluna.architectcoders2024.data.SessionRepository

fun NavGraphBuilder.authNavGraph(
    onNavigate: (Destination) -> Unit
) {
    navigation(
        route = AuthGraph.Init.route(),
        startDestination = AuthGraph.Login.route(),
    ) {
        navComposable(
            destination = AuthGraph.Login
        ) { navBackStackEntry: NavBackStackEntry ->
            val context = LocalContext.current
            val args: String? = navBackStackEntry.arguments?.getString(Default.Auth.name)
            val viewModel: LoginViewModel = viewModel { context.createVM(args) }
            val state by viewModel.state.collectAsState()
            if (state.isLoggedIn) onNavigate(MainGraph.Home)
            LoginScreen(state = state, sendEvent = viewModel::sendEvent)
        }

    }
}


private fun Context.createVM(
    args: String?,
): LoginViewModel {
    val sessionRepository = SessionRepository(app.sessionDatastore)
    val authenticationRepository = AuthenticationRepository(app.client.authenticationService)
    args?.log("args")
    return LoginViewModel(
        args = args,
        repository = authenticationRepository,
        local = sessionRepository
    )
}