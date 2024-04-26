package com.davidluna.architectcoders2024.app.ui.screens.splash.permissions

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.davidluna.architectcoders2024.app.ui.screens.splash.permissions.PermissionState.DENIED

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


