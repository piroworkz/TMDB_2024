package com.davidluna.architectcoders2024.splash.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import com.davidluna.architectcoders2024.core_ui.navigation.composable
import com.davidluna.architectcoders2024.core_ui.navigation.destination.AuthNavigation
import com.davidluna.architectcoders2024.core_ui.navigation.destination.Destination
import com.davidluna.architectcoders2024.core_ui.navigation.destination.StartNavigation
import com.davidluna.architectcoders2024.core_ui.navigation.route
import com.davidluna.architectcoders2024.splash.view.SplashScreen

fun NavGraphBuilder.splashNavGraph(
    navigateTo: (Destination) -> Unit,
) {
    navigation(
        route = StartNavigation.Init.route(),
        startDestination = StartNavigation.Splash.route(),
    ) {
        composable(StartNavigation.Splash) {
            SplashScreen {
                navigateTo(AuthNavigation.Login)
            }
        }
    }
}
