package com.davidluna.architectcoders2024.main_ui.view

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import com.davidluna.architectcoders2024.auth_ui.navigation.authNavGraph
import com.davidluna.architectcoders2024.core_ui.composables.appGradient
import com.davidluna.architectcoders2024.main_ui.presenter.MainEvent
import com.davidluna.architectcoders2024.main_ui.presenter.MainState
import com.davidluna.architectcoders2024.main_ui.view.composables.DrawerScaffoldView
import com.davidluna.architectcoders2024.media_ui.navigation.moviesNavGraph
import com.davidluna.architectcoders2024.navigation.domain.AuthNav
import com.davidluna.architectcoders2024.navigation.domain.StartNav
import com.davidluna.architectcoders2024.splash_ui.navigation.splashNavGraph
import com.davidluna.architectcoders2024.videos_ui.navigation.youtubeNavGraph

@Composable
fun Navigator(
    state: MainState,
    sendEvent: (MainEvent) -> Unit
) = with(rememberNavigatorState()) {

    DrawerScaffoldView(
        state = state,
        sendEvent = sendEvent
    ) { paddingValues: PaddingValues ->
        NavHost(
            navController = controller,
            startDestination = StartNav.Init,
            modifier = Modifier
                .padding(paddingValues)
                .background(appGradient()),
            enterTransition = { fadeIn(tween(0)) },
            exitTransition = { fadeOut(tween(0)) }
        ) {

            splashNavGraph {
                navigateTo(it) {
                    popUpTo(StartNav.Init) {
                        inclusive = true
                    }
                    launchSingleTop = true
                }
            }

            authNavGraph {
                navigateTo(it) {
                    popUpTo(AuthNav.Init) {
                        inclusive = true
                    }
                    launchSingleTop = true
                }
            }

            moviesNavGraph { navigateTo(it) }

            youtubeNavGraph { popBackStack() }
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
private fun AppScaffoldPreView() {
    com.davidluna.architectcoders2024.core_ui.theme.TmdbTheme {
        Navigator(MainState()) {

        }
    }
}