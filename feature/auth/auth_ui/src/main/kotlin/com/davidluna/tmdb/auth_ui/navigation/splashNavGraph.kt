package com.davidluna.tmdb.auth_ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.davidluna.tmdb.auth_ui.view.splash.SplashScreen
import com.davidluna.tmdb.core_ui.navigation.Destination

fun NavGraphBuilder.splashNavGraph(
    navigateHome: () -> Unit,
    navigate: (Destination) -> Unit,
) {
    navigation<InitialNavigation.Init>(
        startDestination = InitialNavigation.Splash()
    ) {
        composable<InitialNavigation.Splash> {
            SplashScreen(
                navigateHome = navigateHome,
                navigate = navigate
            )
        }
    }
}