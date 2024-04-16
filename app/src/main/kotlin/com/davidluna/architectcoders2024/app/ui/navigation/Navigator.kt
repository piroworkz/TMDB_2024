package com.davidluna.architectcoders2024.app.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.davidluna.architectcoders2024.app.ui.common.AppBarView
import com.davidluna.architectcoders2024.app.ui.navigation.Args.IsTopLevelDestination
import com.davidluna.architectcoders2024.app.ui.theme.ArchitectCoders2024Theme

@Composable
fun Navigator(
    modifier: Modifier = Modifier,
    startDestination: Destination = MainGraph.Home,
    content: NavGraphBuilder.() -> Unit
) {

    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryFlow.collectAsState(initial = null)
    var isTopLevelDestination by remember { mutableStateOf(true) }

    backStackEntry?.let {
        isTopLevelDestination = it.arguments?.getBoolean(IsTopLevelDestination().name) ?: false
    }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = { AppBarView(isTopLevelDestination = isTopLevelDestination) }
    ) { paddingValues: PaddingValues ->
        NavHost(
            navController = navController,
            startDestination = startDestination.route(),
            modifier = Modifier.padding(paddingValues)
        ) { content() }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
private fun AppScaffoldPreView() {
    ArchitectCoders2024Theme {
        Navigator {
            navComposable(MainGraph.Home) { Text(text = "Home") }
            navComposable(MainGraph.Detail) { Text(text = "Detail") }
        }
    }
}