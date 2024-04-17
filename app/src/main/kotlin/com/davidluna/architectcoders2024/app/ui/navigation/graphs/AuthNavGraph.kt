package com.davidluna.architectcoders2024.app.ui.navigation.graphs

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import com.davidluna.architectcoders2024.app.ui.navigation.Args
import com.davidluna.architectcoders2024.app.ui.navigation.AuthGraph
import com.davidluna.architectcoders2024.app.ui.navigation.Destination
import com.davidluna.architectcoders2024.app.ui.navigation.MainGraph
import com.davidluna.architectcoders2024.app.ui.navigation.navComposable
import com.davidluna.architectcoders2024.app.ui.navigation.route
import com.davidluna.architectcoders2024.app.ui.screens.login.LoginScreen
import com.davidluna.architectcoders2024.app.ui.screens.login.LoginViewModel

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
            val args = navBackStackEntry.arguments?.getString(Args.Authentication.name)
            val viewModel: LoginViewModel = viewModel { LoginViewModel(args) }
            val state by viewModel.state.collectAsState()
            if (state.userAccountDetail != null) onNavigate(MainGraph.Home)
            LoginScreen(state = state, sendEvent = viewModel::sendEvent)
        }

    }
}