package com.davidluna.architectcoders2024.app.ui.screens.splash

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.davidluna.architectcoders2024.app.ui.screens.splash.PermissionState.DENIED
import com.davidluna.architectcoders2024.app.ui.screens.splash.PermissionState.GRANTED
import com.davidluna.architectcoders2024.app.ui.screens.splash.PermissionState.SHOULD_SHOW_RATIONALE

class PermissionStateHolder(
    val state: MutableState<PermissionState> = mutableStateOf(DENIED),
    private val launcher: ManagedActivityResultLauncher<Array<String>, Map<String, Boolean>>,
    private val requested: MutableState<Boolean>,
) {
    val requestedAtLeastOnce: Boolean
        get() = this.requested.value

    val currentState: PermissionState
        get() = state.value

    fun requestPermission() {
        launcher.launch(arrayOf(ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION))
    }

    fun onDismissRationale() {
        this.requested.value = true
        state.value = DENIED
    }

}


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

enum class PermissionState {
    GRANTED, DENIED, SHOULD_SHOW_RATIONALE
}
