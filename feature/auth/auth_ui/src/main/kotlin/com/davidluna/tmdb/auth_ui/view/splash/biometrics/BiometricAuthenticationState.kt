package com.davidluna.tmdb.auth_ui.view.splash.biometrics

import android.content.Intent
import android.os.Build
import android.provider.Settings
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_STRONG
import androidx.biometric.BiometricManager.Authenticators.DEVICE_CREDENTIAL
import androidx.biometric.BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE
import androidx.biometric.BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED
import androidx.biometric.BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE
import androidx.biometric.BiometricManager.BIOMETRIC_ERROR_SECURITY_UPDATE_REQUIRED
import androidx.biometric.BiometricManager.BIOMETRIC_ERROR_UNSUPPORTED
import androidx.biometric.BiometricManager.BIOMETRIC_STATUS_UNKNOWN
import androidx.biometric.BiometricManager.BIOMETRIC_SUCCESS
import androidx.biometric.BiometricManager.from
import androidx.biometric.BiometricPrompt
import androidx.biometric.BiometricPrompt.AuthenticationCallback
import androidx.biometric.BiometricPrompt.AuthenticationResult
import androidx.biometric.BiometricPrompt.ERROR_CANCELED
import androidx.biometric.BiometricPrompt.ERROR_NO_BIOMETRICS
import androidx.biometric.BiometricPrompt.ERROR_NO_DEVICE_CREDENTIAL
import androidx.biometric.BiometricPrompt.ERROR_TIMEOUT
import androidx.biometric.BiometricPrompt.ERROR_UNABLE_TO_PROCESS
import androidx.biometric.BiometricPrompt.ERROR_USER_CANCELED
import androidx.biometric.BiometricPrompt.PromptInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import com.davidluna.tmdb.auth_ui.presenter.splash.SplashEvent
import com.davidluna.tmdb.auth_ui.presenter.splash.SplashEvent.LaunchBioPrompt
import com.davidluna.tmdb.auth_ui.presenter.splash.SplashEvent.NavigateTo
import com.davidluna.tmdb.auth_ui.presenter.splash.SplashEvent.OnBioPromptResult
import com.davidluna.tmdb.auth_ui.view.splash.biometrics.BioResult.FAILED
import com.davidluna.tmdb.auth_ui.view.splash.biometrics.BioResult.SUCCESS
import com.davidluna.tmdb.auth_ui.view.splash.biometrics.BioResult.UNDEFINED
import com.davidluna.tmdb.core_domain.entities.Session
import com.davidluna.tmdb.core_ui.R
import com.davidluna.tmdb.core_ui.navigation.destination.AuthNavigation
import com.davidluna.tmdb.core_ui.navigation.destination.MediaNavigation
import java.lang.ref.WeakReference
import java.util.concurrent.Executor

class BiometricAuthenticationState(
    private val fragmentActivity: WeakReference<FragmentActivity>,
    private val sendEvent: (event: SplashEvent) -> Unit = {}
) {
    private val executor: Executor? =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            this.fragmentActivity.get()?.mainExecutor
        } else {
            this.fragmentActivity.get()?.applicationContext?.let { ContextCompat.getMainExecutor(it) }
        }

    private val biometricManager: BiometricManager? =
        this.fragmentActivity.get()?.let { from(it.application) }

    private val promptInfo: PromptInfo.Builder? = this.fragmentActivity.get()?.let {
        PromptInfo.Builder()
            .setTitle(it.getString(R.string.biometric_auth_title))
            .setSubtitle(it.getString(R.string.biometric_auth_subtitle))
            .setNegativeButtonText(it.getString(R.string.biometric_use_password_instead))
    }

    @Composable
    fun LaunchEffects(
        session: Session?,
        result: BioResult,
        launchBioPrompt: Boolean,
    ) {
        CanAuthenticateEffect(session)
        BioResultEffect(result = result)
        BiometricsLaunchedEffect(launchBioPrompt)
    }

    @Composable
    private fun CanAuthenticateEffect(session: Session?) {
        LaunchedEffect(session) {
            val result = biometricManager?.canAuthenticate(BIOMETRIC_STRONG or DEVICE_CREDENTIAL)
            when (result) {
                BIOMETRIC_SUCCESS -> {
                    sendEvent(LaunchBioPrompt(true))
                }

                BIOMETRIC_ERROR_NONE_ENROLLED -> {
                    launchEnrollIntent()
                }

                BIOMETRIC_ERROR_HW_UNAVAILABLE,
                BIOMETRIC_ERROR_NO_HARDWARE,
                BIOMETRIC_ERROR_SECURITY_UPDATE_REQUIRED,
                BIOMETRIC_ERROR_UNSUPPORTED,
                BIOMETRIC_STATUS_UNKNOWN,
                -> {
                    sendEvent(NavigateTo(AuthNavigation.Login()))
                }
            }
        }
    }

    @Composable
    private fun BioResultEffect(result: BioResult) {
        LaunchedEffect(result) {
            when (result) {
                SUCCESS -> sendEvent(NavigateTo(MediaNavigation.MediaCatalog()))
                FAILED -> sendEvent(NavigateTo(AuthNavigation.Login()))
                UNDEFINED -> {}
            }
            sendEvent(LaunchBioPrompt(false))
        }
    }

    @Composable
    private fun BiometricsLaunchedEffect(launch: Boolean) {
        LaunchedEffect(launch) {
            if (launch) {
                try {
                    sendEvent(OnBioPromptResult(UNDEFINED))
                    promptInfo?.build()?.let { getBiometricPrompt()?.authenticate(it) }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }

    private fun getBiometricPrompt(): BiometricPrompt? = executor?.let { executor: Executor ->
        fragmentActivity.get()?.let { activity ->
            BiometricPrompt(activity, executor, getCallback())
        }
    }

    private fun getCallback(): AuthenticationCallback =
        object : AuthenticationCallback() {

            override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                super.onAuthenticationError(errorCode, errString)
                when (errorCode) {
                    ERROR_NO_BIOMETRICS, ERROR_NO_DEVICE_CREDENTIAL -> {
                        launchEnrollIntent()
                    }

                    ERROR_TIMEOUT,
                    ERROR_UNABLE_TO_PROCESS,
                    ERROR_USER_CANCELED,
                    ERROR_CANCELED,
                    -> {
                        sendEvent(OnBioPromptResult(UNDEFINED))
                    }

                    else -> {
                        sendEvent(OnBioPromptResult(FAILED))
                    }
                }
                sendEvent(LaunchBioPrompt(false))
            }

            override fun onAuthenticationSucceeded(result: AuthenticationResult) {
                super.onAuthenticationSucceeded(result)
                sendEvent(OnBioPromptResult(SUCCESS))
            }

            override fun onAuthenticationFailed() {
                super.onAuthenticationFailed()
                promptInfo?.setSubtitle(
                    fragmentActivity.get()?.getString(R.string.biometric_auth_failed)
                )
                sendEvent(OnBioPromptResult(FAILED))
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