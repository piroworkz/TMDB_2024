package com.davidluna.tmdb.splash.view.biometrics

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.fragment.app.FragmentActivity
import com.davidluna.tmdb.core_ui.composables.findActivity
import com.davidluna.tmdb.splash.presenter.SplashEvent
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