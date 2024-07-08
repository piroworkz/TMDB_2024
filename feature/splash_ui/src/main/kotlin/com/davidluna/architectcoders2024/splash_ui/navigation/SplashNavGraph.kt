package com.davidluna.architectcoders2024.splash_ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.davidluna.architectcoders2024.navigation.domain.AuthNav
import com.davidluna.architectcoders2024.navigation.domain.Destination
import com.davidluna.architectcoders2024.navigation.domain.StartNav
import com.davidluna.architectcoders2024.splash_ui.view.SplashScreen

fun NavGraphBuilder.splashNavGraph(
    navigateTo: (Destination) -> Unit
) {
    navigation<StartNav.Init>(
        startDestination = StartNav.Splash(true),
    ) {
        composable<StartNav.Splash> {
            SplashScreen { navigateTo(AuthNav.Login()) }
        }
    }
}
