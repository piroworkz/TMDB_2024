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
import com.davidluna.architectcoders2024.app.ui.common.AppBarView
import com.davidluna.architectcoders2024.app.ui.navigation.destinations.AuthGraph
import com.davidluna.architectcoders2024.app.ui.navigation.nav_graphs.authNavGraph
import com.davidluna.architectcoders2024.app.ui.navigation.nav_graphs.mainNavGraph
import com.davidluna.architectcoders2024.app.ui.navigation.navigateTo
import com.davidluna.architectcoders2024.app.ui.navigation.route
import com.davidluna.architectcoders2024.app.ui.screens.login.views.appGradient
import com.davidluna.architectcoders2024.app.ui.theme.ArchitectCoders2024Theme

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
            startDestination = AuthGraph.Init.route(),
            modifier = Modifier
                .padding(paddingValues)
                .background(appGradient())
        ) {

            authNavGraph {
                controller.navigateTo(it) {
                    popUpTo(AuthGraph.Init.route()) { inclusive = true }
                    launchSingleTop = true
                }
            }

            mainNavGraph(navigateTo = { navigateTo(it) })

        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
private fun AppScaffoldPreView() {
    ArchitectCoders2024Theme {
        Navigator()
    }
}