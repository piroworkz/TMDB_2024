package com.davidluna.architectcoders2024.auth_ui.biometrics

import android.content.Intent
import android.os.Build
import android.provider.Settings
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_STRONG
import androidx.biometric.BiometricManager.Authenticators.DEVICE_CREDENTIAL
import androidx.biometric.BiometricPrompt
import androidx.biometric.BiometricPrompt.PromptInfo
import androidx.compose.runtime.MutableState
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import com.davidluna.architectcoders2024.auth_ui.biometrics.BiometricState.ERROR
import com.davidluna.architectcoders2024.auth_ui.biometrics.BiometricState.SUCCESS
import com.davidluna.architectcoders2024.auth_ui.biometrics.BiometricState.UNAVAILABLE
import com.davidluna.architectcoders2024.core_ui.R
import java.lang.ref.WeakReference
import java.util.concurrent.Executor

class BiometricAuthenticationState(
    private val fragmentActivity: WeakReference<FragmentActivity>,
    private val state: MutableState<BiometricState>
) {
    private val executor: Executor? =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            this.fragmentActivity.get()?.mainExecutor
        } else {
            this.fragmentActivity.get()?.applicationContext?.let { ContextCompat.getMainExecutor(it) }
        }

    private val biometricManager: BiometricManager? =
        this.fragmentActivity.get()?.let { BiometricManager.from(it.application) }

    private val promptInfo: PromptInfo.Builder? = this.fragmentActivity.get()?.let {
        PromptInfo.Builder()
            .setTitle(it.getString(R.string.biometric_auth_title))
            .setSubtitle(it.getString(R.string.biometric_auth_subtitle))
            .setNegativeButtonText(it.getString(R.string.biometric_use_password_instead))
    }

    val biometricState: BiometricState
        get() = state.value

    init {
        canAuthenticate()
    }

    fun launchPrompt() {
        try {
            promptInfo?.build()?.let { getBiometricPrompt()?.authenticate(it) }
        } catch (e: Exception) {
            state.value = ERROR
        }
    }

    fun changeState(newState: BiometricState) {
        state.value = newState
    }

    private fun canAuthenticate() {
        when (biometricManager?.canAuthenticate(BIOMETRIC_STRONG or DEVICE_CREDENTIAL)) {


            BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE -> state.value = UNAVAILABLE

            BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED -> launchEnrollIntent()

            BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE -> state.value = UNAVAILABLE

            BiometricManager.BIOMETRIC_ERROR_SECURITY_UPDATE_REQUIRED -> state.value = ERROR

            BiometricManager.BIOMETRIC_ERROR_UNSUPPORTED -> state.value = UNAVAILABLE

            BiometricManager.BIOMETRIC_STATUS_UNKNOWN -> state.value = ERROR

            BiometricManager.BIOMETRIC_SUCCESS -> state.value = BiometricState.SHOW_PROMPT

        }
    }

    private fun getBiometricPrompt(): BiometricPrompt? = executor?.let { executor: Executor ->
        fragmentActivity.get()?.let { activity ->
            BiometricPrompt(activity, executor, getCallback())
        }
    }

    private fun getCallback(): BiometricPrompt.AuthenticationCallback =
        object : BiometricPrompt.AuthenticationCallback() {

            override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                super.onAuthenticationError(errorCode, errString)
                if (errorCode == BiometricPrompt.ERROR_USER_CANCELED) {
                    promptInfo?.setSubtitle(errString)
                    state.value = ERROR
                }
            }

            override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                super.onAuthenticationSucceeded(result)
                state.value = SUCCESS
            }

            override fun onAuthenticationFailed() {
                super.onAuthenticationFailed()
                promptInfo?.setSubtitle(
                    fragmentActivity.get()?.getString(R.string.biometric_auth_failed)
                )
                state.value = ERROR
            }

        }

    private fun launchEnrollIntent() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            Intent(Settings.ACTION_BIOMETRIC_ENROLL).apply {
                putExtra(
                    Settings.EXTRA_BIOMETRIC_AUTHENTICATORS_ALLOWED,
                    BIOMETRIC_STRONG or DEVICE_CREDENTIAL
                )
                fragmentActivity.get()?.startActivity(this)
            }
        }
    }

    fun clearReferences() {
        fragmentActivity.clear()
    }
}