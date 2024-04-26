package com.davidluna.architectcoders2024.app.ui.screens.splash.views

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.davidluna.architectcoders2024.app.ui.screens.splash.permissions.PermissionState.DENIED
import com.davidluna.architectcoders2024.app.ui.screens.splash.permissions.PermissionState.GRANTED
import com.davidluna.architectcoders2024.app.ui.screens.splash.permissions.PermissionState.SHOULD_SHOW_RATIONALE
import com.davidluna.architectcoders2024.app.ui.screens.splash.permissions.PermissionStateHolder

@Composable
fun PermissionLaunchedEffect(
    permissionState: PermissionStateHolder,
    onGranted: () -> Unit
) {
    LaunchedEffect(key1 = permissionState.currentState) {
        when (permissionState.currentState) {
            GRANTED -> {
                onGranted()
            }

            DENIED -> {
                permissionState.requestPermission()
            }

            SHOULD_SHOW_RATIONALE -> {}
        }
    }
}