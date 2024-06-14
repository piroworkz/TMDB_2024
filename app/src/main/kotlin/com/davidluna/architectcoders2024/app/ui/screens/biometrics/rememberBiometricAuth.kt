package com.davidluna.architectcoders2024.app.ui.screens.biometrics

import android.app.Application
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_STRONG
import androidx.biometric.BiometricManager.Authenticators.DEVICE_CREDENTIAL
import androidx.biometric.BiometricManager.BIOMETRIC_SUCCESS
import androidx.biometric.BiometricPrompt
import androidx.biometric.BiometricPrompt.PromptInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import com.davidluna.architectcoders2024.R
import com.davidluna.architectcoders2024.app.ui.screens.biometrics.BiometricAuthState.ERROR
import com.davidluna.architectcoders2024.app.ui.screens.biometrics.BiometricAuthState.SHOW_PROMPT
import com.davidluna.architectcoders2024.app.ui.screens.biometrics.BiometricAuthState.SUCCESS
import com.davidluna.architectcoders2024.app.ui.screens.biometrics.BiometricAuthState.UNAVAILABLE
import com.davidluna.architectcoders2024.app.ui.theme.locals.LocalFragmentActivity
import com.davidluna.architectcoders2024.app.ui.theme.locals.LocalApplication
import com.davidluna.architectcoders2024.app.ui.theme.locals.Locals
import java.util.concurrent.Executor

@Composable
fun rememberBiometricAuth(
    application: Application = Locals.application,
    activity: FragmentActivity = Locals.fragmentActivity,
    executor: Executor = remember { ContextCompat.getMainExecutor(application) },
    promptInfo: PromptInfo.Builder = remember {
        PromptInfo.Builder()
            .setTitle(application.getString(R.string.biometric_auth_title))
            .setSubtitle(application.getString(R.string.biometric_auth_subtitle))
            .setNegativeButtonText(application.getString(R.string.biometric_use_password_instead))
    },
    manager: BiometricManager = remember { BiometricManager.from(application) },
    canAuthenticate: MutableState<Boolean> = remember {
        mutableStateOf(manager.canAuthenticate(BIOMETRIC_STRONG or DEVICE_CREDENTIAL) == BIOMETRIC_SUCCESS)
    },
    state: MutableState<BiometricAuthState> = remember {
        mutableStateOf(if (!canAuthenticate.value) UNAVAILABLE else SHOW_PROMPT)
    },
    biometricPrompt: BiometricPrompt = remember {
        BiometricPrompt(
            activity, executor,
            object : BiometricPrompt.AuthenticationCallback() {

                override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                    super.onAuthenticationError(errorCode, errString)
                    promptInfo.setSubtitle(errString)
                    state.value = ERROR
                }

                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                    super.onAuthenticationSucceeded(result)
                    state.value = SUCCESS
                }

                override fun onAuthenticationFailed() {
                    super.onAuthenticationFailed()
                    promptInfo.setSubtitle(application.getString(R.string.biometric_auth_failed))
                    state.value = SHOW_PROMPT
                }

            })
    }
): BioAuthState = remember(state) { BioAuthState(biometricPrompt, promptInfo, state) }

