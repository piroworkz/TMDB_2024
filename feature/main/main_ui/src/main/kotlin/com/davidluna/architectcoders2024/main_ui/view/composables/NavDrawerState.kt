package com.davidluna.architectcoders2024.main_ui.view.composables

import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class NavDrawerState(
    private val drawerState: DrawerState,
    private val scope: CoroutineScope
) {
    val state: DrawerState = drawerState

    fun toggleState() {
        when (drawerState.currentValue) {
            DrawerValue.Closed -> scope.launch { drawerState.open() }
            DrawerValue.Open -> scope.launch { drawerState.close() }
        }
    }
}

@Composable
fun rememberNavDrawerState(
    drawerState: DrawerState = rememberDrawerState(initialValue = DrawerValue.Closed),
    scope: CoroutineScope
): NavDrawerState = remember { NavDrawerState(drawerState, scope) }