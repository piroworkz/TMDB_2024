package com.davidluna.architectcoders2024.auth_ui.biometrics

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.davidluna.architectcoders2024.auth_ui.presenter.LoginEvent
import com.davidluna.architectcoders2024.navigation.domain.destination.MediaNavigation

@Composable
fun BiometricsLaunchedEffect(
    launchPrompt: Boolean,
    biometricAuthState: BiometricAuthenticationState,
    sendEvent: (LoginEvent) -> Unit
) {
    LaunchedEffect(
        key1 = biometricAuthState.biometricState,
        key2 = launchPrompt
    ) {
        when (biometricAuthState.biometricState) {
            BiometricState.SUCCESS -> {
                sendEvent(LoginEvent.Navigate(MediaNavigation.MediaCatalog))
            }

            BiometricState.SHOW_PROMPT -> {
                if (launchPrompt) {
                    biometricAuthState.launchPrompt()
                }
            }

            BiometricState.ERROR -> {
                // Show error dialog?
            }

            BiometricState.UNAVAILABLE -> {
                // Show error dialog?
            }

        }
    }
}
