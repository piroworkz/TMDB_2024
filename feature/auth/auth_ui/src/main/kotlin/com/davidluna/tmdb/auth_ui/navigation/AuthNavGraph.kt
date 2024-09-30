package com.davidluna.tmdb.auth_ui.navigation

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.navigation.toRoute
import com.davidluna.tmdb.auth_ui.presenter.LoginEvent
import com.davidluna.tmdb.auth_ui.presenter.LoginViewModel
import com.davidluna.tmdb.auth_ui.view.LoginScreen
import com.davidluna.tmdb.auth_ui.view.composables.IntentView
import com.davidluna.tmdb.core_ui.navigation.destination.AuthNavigation
import com.davidluna.tmdb.core_ui.navigation.destination.AuthNavigation.Login
import com.davidluna.tmdb.core_ui.navigation.destination.Destination
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

fun NavGraphBuilder.authNavGraph(
    navigateTo: (Destination) -> Unit,
) {

    navigation<AuthNavigation.Init>(
        startDestination = Login(),
    ) {

        composable<Login>(deepLinks = AuthNavigation.deepLink) {
            val viewModel: LoginViewModel = koinViewModel { parametersOf(it.toRoute<Login>()) }
            val state by viewModel.state.collectAsState()
            state.destination?.let {
                navigateTo(it)
                viewModel.onEvent(LoginEvent.Navigate(null))
            }
            if (state.launchTMDBWeb) {
                IntentView(state.token)
            }
            LoginScreen(state = state) { viewModel.onEvent(it) }
        }

    }
}
