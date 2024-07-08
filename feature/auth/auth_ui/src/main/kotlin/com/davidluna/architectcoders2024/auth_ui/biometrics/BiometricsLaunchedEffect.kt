package com.davidluna.architectcoders2024.auth_ui.biometrics

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.davidluna.architectcoders2024.auth_ui.presenter.LoginEvent
import com.davidluna.architectcoders2024.navigation.domain.MediaNavigation

@Composable
fun BiometricsLaunchedEffect(
    biometricAuthState: BiometricAuthenticationState,
    sendEvent: (LoginEvent) -> Unit
) {
    LaunchedEffect(
        key1 = biometricAuthState.biometricState,
    ) {
        when (biometricAuthState.biometricState) {
            BiometricState.SUCCESS -> {
                sendEvent(LoginEvent.IsLoggedIn(MediaNavigation.Movies()))
            }

            BiometricState.SHOW_PROMPT -> {
                biometricAuthState.launchPrompt()
            }

            else -> {}
        }
    }
}
