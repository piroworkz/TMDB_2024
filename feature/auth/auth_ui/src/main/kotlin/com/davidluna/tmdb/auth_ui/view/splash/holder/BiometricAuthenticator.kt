package com.davidluna.tmdb.auth_ui.view.splash.holder

import androidx.biometric.BiometricManager.Authenticators
import androidx.biometric.BiometricManager.BIOMETRIC_SUCCESS
import androidx.biometric.BiometricManager.from
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import com.davidluna.tmdb.auth_ui.navigation.AuthNavigation
import com.davidluna.tmdb.core_ui.R
import com.davidluna.tmdb.core_ui.navigation.Destination
import java.util.concurrent.Executor

class BiometricAuthenticator(
    private val activity: FragmentActivity?,
    private val navigateHome: () -> Unit,
    private val navigate: (Destination) -> Unit,
) {

    fun showBiometricPrompt() {
        if (activity == null) return

        if (!activity.canAuthenticate()) {
            navigate(AuthNavigation.Login())
        } else {
            val executor = ContextCompat.getMainExecutor(activity)
            val callback = object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                    navigateHome()
                }

                override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                    navigate(AuthNavigation.Login())
                }

                override fun onAuthenticationFailed() {}
            }
            activity.launchPrompt(executor, callback)
        }
    }

    private fun FragmentActivity.launchPrompt(
        executor: Executor,
        callback: BiometricPrompt.AuthenticationCallback,
    ) {
        try {
            val biometricPrompt = BiometricPrompt(this, executor, callback)
            val promptInfo = BiometricPrompt.PromptInfo.Builder()
                .setTitle(getString(R.string.bioprompt_title))
                .setSubtitle(getString(R.string.bioprompt_subtitle))
                .setAllowedAuthenticators(Authenticators.BIOMETRIC_STRONG or Authenticators.DEVICE_CREDENTIAL)
                .build()
            biometricPrompt.authenticate(promptInfo)
        } catch (_: Exception) {
            navigate(AuthNavigation.Login())
        }
    }

    private fun FragmentActivity.canAuthenticate(): Boolean {
        val biometricManager = from(this)
        val authenticators: Int =
            Authenticators.BIOMETRIC_STRONG or Authenticators.DEVICE_CREDENTIAL
        val canAuthenticate = biometricManager.canAuthenticate(authenticators) == BIOMETRIC_SUCCESS
        return canAuthenticate
    }

}