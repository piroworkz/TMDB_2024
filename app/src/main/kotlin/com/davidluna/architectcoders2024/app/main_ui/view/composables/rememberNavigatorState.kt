package com.davidluna.architectcoders2024.app.main_ui.view.composables

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.davidluna.architectcoders2024.app.main_ui.view.NavigatorState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.Flow

@Composable
fun rememberNavigatorState(
    navController: NavHostController = rememberNavController(),
    backStackEntry: Flow<NavBackStackEntry> = navController.currentBackStackEntryFlow,
    scope: CoroutineScope = rememberCoroutineScope(),
    drawerState: NavDrawerState = rememberNavDrawerState(scope = scope),
): NavigatorState {
    val navigatorState = remember {
        NavigatorState(navController, drawerState, backStackEntry, scope)
    }

    DisposableEffect(Unit) {
        onDispose {
            scope.cancel()
        }
    }

    return navigatorState
}
