package com.davidluna.tmdb.auth_ui.view.splash.biometrics

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.fragment.app.FragmentActivity
import com.davidluna.tmdb.auth_ui.presenter.splash.SplashEvent
import com.davidluna.tmdb.core_ui.composables.findActivity
import java.lang.ref.WeakReference

@Composable
fun rememberBiometricAuth(
    context: Context = LocalContext.current,
    sendEvent: (event: SplashEvent) -> Unit = {},
): BiometricAuthenticationState {
    val biometricState = remember(context) {
        BiometricAuthenticationState(
            fragmentActivity = WeakReference(context.findActivity() as FragmentActivity),
            sendEvent = { sendEvent(it) }
        )
    }
    DisposableEffect(context) {
        onDispose {
            biometricState.clearReferences()
        }
    }

    return biometricState
}