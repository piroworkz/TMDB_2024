package com.davidluna.architectcoders2024.app.ui.screens.navigator

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
import com.davidluna.architectcoders2024.app.MainViewModel
import com.davidluna.architectcoders2024.app.ui.composables.AppBarView
import com.davidluna.architectcoders2024.app.ui.composables.NavDrawerView
import com.davidluna.architectcoders2024.app.ui.navigation.destinations.AuthNav
import com.davidluna.architectcoders2024.app.ui.navigation.destinations.Destination
import com.davidluna.architectcoders2024.app.ui.navigation.destinations.StartNav
import com.davidluna.architectcoders2024.app.ui.navigation.nav_graphs.authNavGraph
import com.davidluna.architectcoders2024.app.ui.navigation.nav_graphs.moviesNavGraph
import com.davidluna.architectcoders2024.app.ui.navigation.nav_graphs.splashNavGraph
import com.davidluna.architectcoders2024.app.ui.screens.login.views.appGradient
import com.davidluna.architectcoders2024.app.ui.theme.TmdbTheme
import com.davidluna.architectcoders2024.domain.ContentKind

@Composable
fun Navigator(
    state: MainViewModel.State,
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
                startDestination = StartNav.Init,
                modifier = Modifier
                    .padding(paddingValues)
                    .background(appGradient()),
                enterTransition = { slideIntoContainer(End) },
                exitTransition = { slideOutOfContainer(Start) }
            ) {

                splashNavGraph {
                    navigateTo(it) {
                        popUpTo(StartNav.Init) { inclusive = true }
                        launchSingleTop = true
                    }
                }

                authNavGraph {
                    navigateTo(it) {
                        popUpTo(AuthNav.Init) { inclusive = true }
                        launchSingleTop = true
                    }
                }

                moviesNavGraph(
                    contentKind = state.contentKind,
                    navigateTo = { navigateTo(it) },
                    navigateUp = { popBackStack() }
                )

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
    TmdbTheme {
        Navigator(MainViewModel.State()) {

        }
    }
}