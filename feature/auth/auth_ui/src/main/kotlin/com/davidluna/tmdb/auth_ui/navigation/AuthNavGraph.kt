package com.davidluna.tmdb.auth_ui.navigation

import android.content.Intent
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavDeepLink
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
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
    val deepLink: List<NavDeepLink> = listOf(navDeepLink<Login>(
        basePath = "https://deeplinks-d440b.web.app/login/"
    ) {
        action = Intent.ACTION_VIEW
    })

    navigation<AuthNavigation.Init>(
        startDestination = Login(),
    ) {

        composable<Login>(deepLinks = deepLink) {
            val viewModel: LoginViewModel = koinViewModel { parametersOf(it.toRoute<Login>()) }
            val state by viewModel.state.collectAsState()
            state.destination?.let {
                navigateTo(it)
                viewModel.sendEvent(LoginEvent.Navigate(null))
            }
            if (state.launchTMDBWeb) {
                IntentView(state.token)
            }
            LoginScreen(state = state) { viewModel.sendEvent(it) }
        }

    }
}
