package com.davidluna.tmdb.app.main_ui.view

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.davidluna.tmdb.app.main_ui.presenter.MainEvent
import com.davidluna.tmdb.app.main_ui.presenter.MainViewModel
import com.davidluna.tmdb.app.main_ui.view.composables.DrawerScaffoldView
import com.davidluna.tmdb.app.main_ui.view.composables.rememberNavigatorState
import com.davidluna.tmdb.auth_ui.navigation.authNavGraph
import com.davidluna.tmdb.core_ui.navigation.destination.AuthNavigation
import com.davidluna.tmdb.core_ui.navigation.destination.StartNavigation
import com.davidluna.tmdb.core_ui.theme.TmdbTheme
import com.davidluna.tmdb.media_ui.navigation.mediaNavGraph
import com.davidluna.tmdb.splash.navigation.splashNavGraph
import com.davidluna.tmdb.videos_ui.navigation.youtubeNavGraph

@Composable
fun Navigator(
    state: MainViewModel.MainState,
    sendEvent: (MainEvent) -> Unit,
) {
    val navController = rememberNavController()
    val navigatorState = rememberNavigatorState(navController)
    navigatorState.BackStackEntryFlowCollectEffect()

    DrawerScaffoldView(
        state = state,
        navigatorState = navigatorState,
        sendEvent = { sendEvent(it) }
    ) { paddingValues: PaddingValues ->
        NavHost(
            navController = navController,
            startDestination = StartNavigation.Init,
            modifier = Modifier
                .padding(paddingValues),
            enterTransition = { EnterTransition.None },
            exitTransition = { ExitTransition.None }
        ) {
            splashNavGraph {
                navigatorState.navigateTo(it) {
                    popUpTo(StartNavigation.Init) { inclusive = true }
                    launchSingleTop = true
                }
            }
            authNavGraph {
                navigatorState.navigateTo(it) {
                    popUpTo(AuthNavigation.Init) { inclusive = true }
                    launchSingleTop = true
                }
            }
            mediaNavGraph { navigatorState.navigateTo(it) }
            youtubeNavGraph { navigatorState.popBackStack() }
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
private fun AppScaffoldPreView() {
    TmdbTheme {
        Navigator(MainViewModel.MainState()) {

        }
    }
}
