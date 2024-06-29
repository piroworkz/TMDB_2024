package com.davidluna.architectcoders2024.splash_ui.biometrics

import android.os.Build
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_STRONG
import androidx.biometric.BiometricManager.Authenticators.DEVICE_CREDENTIAL
import androidx.biometric.BiometricManager.BIOMETRIC_SUCCESS
import androidx.biometric.BiometricPrompt
import androidx.biometric.BiometricPrompt.PromptInfo
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import com.davidluna.architectcoders2024.core_ui.R
import com.davidluna.architectcoders2024.core_ui.log
import com.davidluna.architectcoders2024.splash_ui.biometrics.BiometricState.SHOW_PROMPT
import com.davidluna.architectcoders2024.splash_ui.biometrics.BiometricState.UNAVAILABLE
import java.util.concurrent.Executor

class BiometricAuthenticationState(
    private val fragmentActivity: FragmentActivity,
    private val state: MutableState<BiometricState>
) {

    private val executor: Executor? =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            fragmentActivity.mainExecutor
        } else {
            ContextCompat.getMainExecutor(fragmentActivity.applicationContext)
        }

    private val biometricManager: BiometricManager =
        BiometricManager.from(fragmentActivity.application)

    private val promptInfo: PromptInfo.Builder = PromptInfo.Builder()
        .setTitle(fragmentActivity.getString(R.string.biometric_auth_title))
        .setSubtitle(fragmentActivity.getString(R.string.biometric_auth_subtitle))
        .setNegativeButtonText(fragmentActivity.getString(R.string.biometric_use_password_instead))

    private val canAuthenticate: MutableState<Boolean> =
        mutableStateOf(biometricManager.canAuthenticate(BIOMETRIC_STRONG or DEVICE_CREDENTIAL) == BIOMETRIC_SUCCESS)

    val biometricState: BiometricState
        get() = state.value

    init {
        state.value = if (canAuthenticate.value) SHOW_PROMPT else UNAVAILABLE
    }

    fun launchPrompt() {
        try {
            getBiometricPrompt()?.authenticate(promptInfo.build())
        } catch (e: Exception) {
            state.value = BiometricState.ERROR
        }
    }

    private fun getBiometricPrompt(): BiometricPrompt? = executor?.let { executor: Executor ->
        BiometricPrompt(fragmentActivity, executor, getCallback())
    }

    private fun getCallback(): BiometricPrompt.AuthenticationCallback =
        object : BiometricPrompt.AuthenticationCallback() {

            override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                super.onAuthenticationError(errorCode, errString)
                promptInfo.setSubtitle(errString)
                state.value = BiometricState.ERROR
            }

            override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                super.onAuthenticationSucceeded(result)
                state.value = BiometricState.SUCCESS
            }

            override fun onAuthenticationFailed() {
                super.onAuthenticationFailed()
                promptInfo.setSubtitle(fragmentActivity.getString(R.string.biometric_auth_failed))
                state.value = SHOW_PROMPT
            }

        }

}
