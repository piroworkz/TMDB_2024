package com.davidluna.tmdb.auth_ui.navigation

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.navigation.toRoute
import com.davidluna.tmdb.auth_ui.presenter.login.LoginEvent
import com.davidluna.tmdb.auth_ui.presenter.login.LoginViewModel
import com.davidluna.tmdb.auth_ui.presenter.splash.SplashEvent
import com.davidluna.tmdb.auth_ui.presenter.splash.SplashViewModel
import com.davidluna.tmdb.auth_ui.view.login.LoginScreen
import com.davidluna.tmdb.auth_ui.view.splash.SplashScreen
import com.davidluna.tmdb.core_ui.navigation.destination.AuthNavigation
import com.davidluna.tmdb.core_ui.navigation.destination.AuthNavigation.Login
import com.davidluna.tmdb.core_ui.navigation.destination.AuthNavigation.Splash
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

            LoginScreen(
                state = state,
                sendEvent = { viewModel.onEvent(it) }
            )
        }

        composable<Splash> {
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