package com.davidluna.tmdb.splash.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.davidluna.tmdb.core_ui.navigation.destination.AuthNavigation
import com.davidluna.tmdb.core_ui.navigation.destination.Destination
import com.davidluna.tmdb.core_ui.navigation.destination.StartNavigation
import com.davidluna.tmdb.splash.view.SplashScreen

fun NavGraphBuilder.splashNavGraph(
    navigateTo: (Destination) -> Unit,
) {

    navigation<StartNavigation.Init>(
        startDestination = StartNavigation.Splash()
    ) {
        composable<StartNavigation.Splash> {
            SplashScreen {
                navigateTo(AuthNavigation.Login())
            }
        }
    }
}
