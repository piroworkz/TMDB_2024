package com.davidluna.architectcoders2024.main_ui.view

import androidx.compose.animation.AnimatedContentTransitionScope.SlideDirection.Companion.End
import androidx.compose.animation.AnimatedContentTransitionScope.SlideDirection.Companion.Start
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import com.davidluna.architectcoders2024.auth_ui.navigation.authNavGraph
import com.davidluna.architectcoders2024.core_domain.core_entities.ContentKind
import com.davidluna.architectcoders2024.core_ui.composables.AppBarView
import com.davidluna.architectcoders2024.main_ui.view.composables.NavDrawerView
import com.davidluna.architectcoders2024.main_ui.presenter.MainState
import com.davidluna.architectcoders2024.media_ui.navigation.moviesNavGraph
import com.davidluna.architectcoders2024.navigation.model.Destination
import com.davidluna.architectcoders2024.splash_ui.navigation.splashNavGraph
import com.davidluna.architectcoders2024.videos_ui.navigation.youtubeNavGraph

@Composable
fun Navigator(
    state: MainState,
    sendEvent: (ContentKind) -> Unit
) = with(rememberNavigatorState()) {

    ModalNavigationDrawer(
        drawerContent = {
            if (isTopLevel) {
                NavDrawerView(
                    isGuest = state.user == null,
                    user = state.user
                ) {
                    it?.destination?.let { destination: Destination ->
                        navigateTo(destination)
                    }
                    it?.contentKind?.let(sendEvent)
                    drawer.toggleState()
                }
            }
        },
        drawerState = drawer.state,
        gesturesEnabled = isTopLevel
    ) {
        Scaffold(
            modifier = Modifier
                .fillMaxSize(),
            topBar = {
                AppBarView(
                    topLevel = isTopLevel,
                    hideAppBar = hideAppBar,
                    onNavigationIconClick = { onNavClick() }
                )
            }
        ) { paddingValues: PaddingValues ->
            NavHost(
                navController = controller,
                startDestination = com.davidluna.architectcoders2024.navigation.model.StartNav.Init,
                modifier = Modifier
                    .padding(paddingValues)
                    .background(com.davidluna.architectcoders2024.core_ui.composables.appGradient()),
                enterTransition = { slideIntoContainer(End) },
                exitTransition = { slideOutOfContainer(Start) }
            ) {

                splashNavGraph {
                    navigateTo(it) {
                        popUpTo(com.davidluna.architectcoders2024.navigation.model.StartNav.Init) {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                }

                authNavGraph {
                    navigateTo(it) {
                        popUpTo(com.davidluna.architectcoders2024.navigation.model.AuthNav.Init) {
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