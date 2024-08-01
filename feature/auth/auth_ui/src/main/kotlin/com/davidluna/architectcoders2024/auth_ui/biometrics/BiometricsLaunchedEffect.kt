package com.davidluna.architectcoders2024.auth_ui.biometrics

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect

@Composable
fun BiometricsLaunchedEffect(
    launch: Boolean,
    state: BiometricAuthenticationState,
) {
    LaunchedEffect(launch) {
        if (launch) {
            state.launchPrompt()
        }
    }
}
