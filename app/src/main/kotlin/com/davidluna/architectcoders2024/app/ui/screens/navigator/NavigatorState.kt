package com.davidluna.architectcoders2024.app.ui.screens.navigator

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.davidluna.architectcoders2024.app.ui.navigation.destinations.Destination
import com.davidluna.architectcoders2024.app.ui.navigation.navigateTo
import com.davidluna.architectcoders2024.app.ui.navigation.safe_args.Default
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class NavigatorState(
    private val navController: NavHostController,
    backStackEntry: Flow<NavBackStackEntry>,
    scope: CoroutineScope
) {

    init {
        collectFlow(scope, backStackEntry)
    }

    val controller
        get() = navController

    var isTopLevel by mutableStateOf(false)
        private set

    var hideAppBar by mutableStateOf(false)
        private set

    fun popBackStack() {
        navController.popBackStack()
    }

    fun navigateTo(destination: Destination) {
        navController.navigateTo(destination)
    }

    private fun collectFlow(
        scope: CoroutineScope,
        backStackEntry: Flow<NavBackStackEntry>
    ) {
        scope.launch {
            backStackEntry.collect {
                it.arguments?.let { args ->
                    isTopLevel = args.getBoolean(Default.TopLevel.name)
                    hideAppBar = args.getBoolean(Default.HideAppBar.name)
                }
            }
        }
    }
}

@Composable
fun rememberNavigatorState(
    navController: NavHostController = rememberNavController(),
    backStackEntry: Flow<NavBackStackEntry> = navController.currentBackStackEntryFlow,
    scope: CoroutineScope = rememberCoroutineScope()
) = remember {
    NavigatorState(navController, backStackEntry, scope)
}