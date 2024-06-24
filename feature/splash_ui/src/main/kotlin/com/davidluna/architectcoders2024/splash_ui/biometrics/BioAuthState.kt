package com.davidluna.architectcoders2024.splash_ui.biometrics

import androidx.biometric.BiometricPrompt
import androidx.biometric.BiometricPrompt.PromptInfo
import androidx.compose.runtime.MutableState

class BioAuthState(
    private val _biometricPrompt: BiometricPrompt?,
    private val promptInfo: PromptInfo.Builder,
    private val state: MutableState<BiometricAuthState>
) {

    val currentState: BiometricAuthState
        get() = state.value

    fun launchPrompt() {
        _biometricPrompt?.authenticate(promptInfo.build())
    }

}
