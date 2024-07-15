package com.davidluna.architectcoders2024.splash_ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import com.davidluna.architectcoders2024.navigation.domain.composable
import com.davidluna.architectcoders2024.navigation.domain.destination.AuthNavigation
import com.davidluna.architectcoders2024.navigation.domain.destination.Destination
import com.davidluna.architectcoders2024.navigation.domain.destination.StartNavigation
import com.davidluna.architectcoders2024.navigation.domain.route
import com.davidluna.architectcoders2024.splash_ui.view.SplashScreen

fun NavGraphBuilder.splashNavGraph(
    navigateTo: (Destination) -> Unit
) {
    navigation(
        route = StartNavigation.Init.route(),
        startDestination = StartNavigation.Splash.route(),
    ) {
        composable(StartNavigation.Splash) {
            SplashScreen { navigateTo(AuthNavigation.Login) }
        }
    }
}
