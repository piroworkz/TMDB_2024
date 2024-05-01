package com.davidluna.architectcoders2024.app.ui.screens.splash.views

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.davidluna.architectcoders2024.app.ui.screens.biometrics.BioAuthState
import com.davidluna.architectcoders2024.app.ui.screens.biometrics.BiometricAuthState
import com.davidluna.architectcoders2024.app.ui.screens.splash.SplashEvent
import com.davidluna.architectcoders2024.app.ui.screens.splash.SplashViewModel

@Composable
fun BiometricsLaunchedEffect(
    biometricAuthState: BioAuthState,
    state: SplashViewModel.State,
    sendEvent: (SplashEvent) -> Unit
) {
    LaunchedEffect(
        key1 = biometricAuthState.currentState,
        key2 = state.isGranted,
        key3 = state.sessionExists
    ) {
        when (biometricAuthState.currentState) {
            BiometricAuthState.SUCCESS -> {
                sendEvent(SplashEvent.OnLoggedIn)
            }

            BiometricAuthState.SHOW_PROMPT -> {
                if (state.isGranted && state.sessionExists) {
                    biometricAuthState.launchPrompt()
                }
                if (state.isGranted && !state.sessionExists) {
                    sendEvent(SplashEvent.OnBioFailed)
                }
            }

            else -> {
                sendEvent(SplashEvent.OnBioFailed)
            }
        }
    }
}