package com.davidluna.tmdb.app.main_ui.view.composables

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavHostController
import com.davidluna.tmdb.app.main_ui.view.NavigatorState
import kotlinx.coroutines.CoroutineScope

@Composable
fun rememberNavigatorState(
    navController: NavHostController,
    scope: CoroutineScope = rememberCoroutineScope(),
): NavigatorState = remember(navController, scope) {
    NavigatorState(
        navController = navController,
        scope = scope
    )
}