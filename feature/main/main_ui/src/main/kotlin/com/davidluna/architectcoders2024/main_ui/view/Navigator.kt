package com.davidluna.architectcoders2024.main_ui.view

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import com.davidluna.architectcoders2024.auth_ui.navigation.authNavGraph
import com.davidluna.architectcoders2024.main_ui.presenter.MainEvent
import com.davidluna.architectcoders2024.main_ui.presenter.MainState
import com.davidluna.architectcoders2024.main_ui.view.composables.DrawerScaffoldView
import com.davidluna.architectcoders2024.main_ui.view.composables.rememberNavigatorState
import com.davidluna.architectcoders2024.media_ui.navigation.mediaNavGraph
import com.davidluna.architectcoders2024.navigation.domain.destination.AuthNavigation
import com.davidluna.architectcoders2024.navigation.domain.destination.StartNavigation
import com.davidluna.architectcoders2024.navigation.domain.route
import com.davidluna.architectcoders2024.splash_ui.navigation.splashNavGraph
import com.davidluna.architectcoders2024.videos_ui.navigation.youtubeNavGraph

@Composable
fun Navigator(
    state: MainState,
    sendEvent: (MainEvent) -> Unit
) = with(rememberNavigatorState()) {

    DrawerScaffoldView(
        state = state,
        appBarTitle = appBarTitle,
        sendEvent = { sendEvent(it) }
    ) { paddingValues: PaddingValues ->
        NavHost(
            navController = controller,
            startDestination = StartNavigation.Init.route(),
            modifier = Modifier
                .padding(paddingValues),
            enterTransition = { EnterTransition.None },
            exitTransition = { ExitTransition.None }
        ) {

            splashNavGraph {
                navigateTo(it) {
                    popUpTo(StartNavigation.Init.route()) { inclusive = true }
                    launchSingleTop = true
                }
            }

            authNavGraph {
                navigateTo(it) {
                    popUpTo(AuthNavigation.Init.route()) { inclusive = true }
                    launchSingleTop = true
                }
            }

            mediaNavGraph { navigateTo(it) }

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
