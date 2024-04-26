package com.davidluna.architectcoders2024.app.ui.screens.splash.permissions

import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.davidluna.architectcoders2024.app.ui.screens.splash.permissions.PermissionState.DENIED
import com.davidluna.architectcoders2024.app.ui.screens.splash.permissions.PermissionState.GRANTED
import com.davidluna.architectcoders2024.app.ui.screens.splash.permissions.PermissionState.SHOULD_SHOW_RATIONALE

@Composable
fun rememberPermissionState(
    state: MutableState<PermissionState> = remember { mutableStateOf(DENIED) },
    requestedAtLeastOnce: MutableState<Boolean> = remember { mutableStateOf(false) },
    launcher: ManagedActivityResultLauncher<Array<String>, Map<String, Boolean>> = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions(),
    ) { results ->
        if (results.all { it.value }) {
            state.value = GRANTED
        } else {
            state.value = SHOULD_SHOW_RATIONALE
        }
    }
): PermissionStateHolder =
    remember(state, requestedAtLeastOnce) {
        PermissionStateHolder(
            state = state,
            launcher = launcher,
            requested = requestedAtLeastOnce
        )
    }

