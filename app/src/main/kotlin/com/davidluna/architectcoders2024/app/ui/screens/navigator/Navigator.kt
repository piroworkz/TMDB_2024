package com.davidluna.architectcoders2024.app.ui.screens.navigator

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import com.davidluna.architectcoders2024.app.ui.composables.AppBarView
import com.davidluna.architectcoders2024.app.ui.navigation.destinations.AuthNav
import com.davidluna.architectcoders2024.app.ui.navigation.destinations.StartNav
import com.davidluna.architectcoders2024.app.ui.navigation.nav_graphs.authNavGraph
import com.davidluna.architectcoders2024.app.ui.navigation.nav_graphs.moviesNavGraph
import com.davidluna.architectcoders2024.app.ui.navigation.nav_graphs.splashNavGraph
import com.davidluna.architectcoders2024.app.ui.screens.login.views.appGradient
import com.davidluna.architectcoders2024.app.ui.theme.TmdbTheme

@Composable
fun Navigator() = with(rememberNavigatorState()) {

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            AppBarView(
                topLevel = isTopLevel,
                hideAppBar = hideAppBar,
                onNavigationIconClick = { popBackStack() }
            )
        }
    ) { paddingValues: PaddingValues ->
        NavHost(
            navController = controller,
            startDestination = StartNav.Init,
            modifier = Modifier
                .padding(paddingValues)
                .background(appGradient())
        ) {

            splashNavGraph {
                controller.navigate(it) {
                    popUpTo(StartNav.Init) { inclusive = true }
                    launchSingleTop = true
                }
            }

            authNavGraph {
                controller.navigate(it) {
                    popUpTo(AuthNav.Init) { inclusive = true }
                    launchSingleTop = true
                }
            }

            moviesNavGraph(
                navigateTo = { navigateTo(it) },
                navigateUp = { popBackStack() }
            )

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
        Navigator()
    }
}