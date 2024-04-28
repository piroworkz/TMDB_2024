package com.davidluna.architectcoders2024.app.ui.screens.biometrics

import android.content.Context
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_STRONG
import androidx.biometric.BiometricManager.Authenticators.DEVICE_CREDENTIAL
import androidx.biometric.BiometricManager.BIOMETRIC_SUCCESS
import androidx.biometric.BiometricPrompt
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import com.davidluna.architectcoders2024.R
import com.davidluna.architectcoders2024.app.ui.screens.biometrics.BiometricAuthState.ERROR
import com.davidluna.architectcoders2024.app.ui.screens.biometrics.BiometricAuthState.SHOW_PROMPT
import com.davidluna.architectcoders2024.app.ui.screens.biometrics.BiometricAuthState.SUCCESS
import com.davidluna.architectcoders2024.app.ui.screens.biometrics.BiometricAuthState.UNAVAILABLE
import java.util.concurrent.Executor

class BioAuthState(
    private val context: Context,
    private val executor: Executor,
    private val state: MutableState<BiometricAuthState>
) {

    private val promptInfo = BiometricPrompt.PromptInfo.Builder()
        .setTitle(context.getString(R.string.biometric_auth_title))
        .setSubtitle(context.getString(R.string.biometric_auth_subtitle))
        .setNegativeButtonText(context.getString(R.string.biometric_use_password_instead))

    val currentState: BiometricAuthState
        get() = state.value

    fun launchPrompt() {
        getPrompt().authenticate(promptInfo.build())
    }


    private fun getPrompt() = BiometricPrompt(
        context as FragmentActivity, executor,
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
                promptInfo.setSubtitle(context.getString(R.string.biometric_auth_failed))
                state.value = SHOW_PROMPT
            }

        })

}


@Composable
fun rememberBiometricAuth(
    context: Context = LocalContext.current,
    executor: Executor = remember { ContextCompat.getMainExecutor(context) },
    manager: BiometricManager = remember { BiometricManager.from(context) },
    canAuthenticate: MutableState<Boolean> = remember {
        mutableStateOf(manager.canAuthenticate(BIOMETRIC_STRONG or DEVICE_CREDENTIAL) == BIOMETRIC_SUCCESS)
    },
    state: MutableState<BiometricAuthState> = remember {
        mutableStateOf(if (!canAuthenticate.value) UNAVAILABLE else SHOW_PROMPT)
    },
): BioAuthState = remember { BioAuthState(context, executor, state) }

