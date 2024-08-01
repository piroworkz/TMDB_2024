package com.davidluna.architectcoders2024.auth_ui.biometrics

import android.content.Intent
import android.os.Build
import android.provider.Settings
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_STRONG
import androidx.biometric.BiometricManager.Authenticators.DEVICE_CREDENTIAL
import androidx.biometric.BiometricPrompt
import androidx.biometric.BiometricPrompt.PromptInfo
import androidx.compose.runtime.mutableStateOf
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import com.davidluna.architectcoders2024.core_ui.R
import java.lang.ref.WeakReference
import java.util.concurrent.Executor

class BiometricAuthenticationState(
    private val fragmentActivity: WeakReference<FragmentActivity>,
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

    val printAuthenticated = mutableStateOf(BioResult.UNDEFINED)

    fun canAuthenticate(onResult: (Boolean) -> Unit) {
        val result = biometricManager?.canAuthenticate(BIOMETRIC_STRONG or DEVICE_CREDENTIAL)
        when (result) {
            BiometricManager.BIOMETRIC_SUCCESS -> onResult(true)
            BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED -> launchEnrollIntent()
            else -> onResult(false)
        }
    }

    fun launchPrompt() {
        try {
            printAuthenticated.value = BioResult.UNDEFINED
            promptInfo?.build()?.let { getBiometricPrompt()?.authenticate(it) }
        } catch (e: Exception) {
            e.printStackTrace()
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
                }
                if (errorCode == BiometricPrompt.ERROR_NEGATIVE_BUTTON) {
                    promptInfo?.setSubtitle(errString)
                }
                printAuthenticated.value = BioResult.FAILED
            }

            override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                super.onAuthenticationSucceeded(result)
                printAuthenticated.value = BioResult.SUCCESS
            }

            override fun onAuthenticationFailed() {
                super.onAuthenticationFailed()
                promptInfo?.setSubtitle(
                    fragmentActivity.get()?.getString(R.string.biometric_auth_failed)
                )
                printAuthenticated.value = BioResult.FAILED
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