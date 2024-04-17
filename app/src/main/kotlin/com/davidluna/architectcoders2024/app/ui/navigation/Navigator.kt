package com.davidluna.architectcoders2024.app.ui.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.davidluna.architectcoders2024.app.ui.common.AppBarView
import com.davidluna.architectcoders2024.app.ui.navigation.Args.IsTopLevelDestination
import com.davidluna.architectcoders2024.app.ui.navigation.graphs.authNavGraph
import com.davidluna.architectcoders2024.app.ui.navigation.graphs.mainNavGraph
import com.davidluna.architectcoders2024.app.ui.screens.login.views.appGradient
import com.davidluna.architectcoders2024.app.ui.theme.ArchitectCoders2024Theme

@Composable
fun Navigator() {

    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryFlow.collectAsState(initial = null)
    var isTopLevelDestination by remember { mutableStateOf(true) }
    var showAppBar by remember { mutableStateOf(true) }

    backStackEntry?.let { navBackStackEntry ->
        isTopLevelDestination =
            navBackStackEntry.arguments?.getBoolean(IsTopLevelDestination().name) ?: false
        showAppBar = navBackStackEntry.destination.route != "login"
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = { if (showAppBar) AppBarView(isTopLevelDestination = isTopLevelDestination) }
    ) { paddingValues: PaddingValues ->

        NavHost(
            navController = navController,
            startDestination = AuthGraph.Init.route(),
            modifier = Modifier
                .padding(paddingValues)
                .background(appGradient())
        ) {

            authNavGraph {
                navController.navigateTo(it) {
                    popUpTo(AuthGraph.Init.route()) { inclusive = true }
                    launchSingleTop = true
                }
            }

            mainNavGraph()

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