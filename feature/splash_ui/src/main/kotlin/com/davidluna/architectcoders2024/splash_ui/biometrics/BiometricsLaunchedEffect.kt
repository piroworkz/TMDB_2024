package com.davidluna.architectcoders2024.splash_ui.biometrics

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.davidluna.architectcoders2024.splash_ui.presenter.SplashEvent
import com.davidluna.architectcoders2024.splash_ui.presenter.SplashState

@Composable
fun BiometricsLaunchedEffect(
    biometricAuthState: BioAuthState,
    state: SplashState,
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