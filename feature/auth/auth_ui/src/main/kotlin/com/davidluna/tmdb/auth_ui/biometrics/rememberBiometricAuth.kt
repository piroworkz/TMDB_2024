package com.davidluna.tmdb.auth_ui.biometrics

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.fragment.app.FragmentActivity
import com.davidluna.tmdb.core_ui.composables.findActivity
import java.lang.ref.WeakReference

@Composable
fun rememberBiometricAuth(
    context: Context = LocalContext.current,
): BiometricAuthenticationState {
    val biometricState = remember(context) {
        BiometricAuthenticationState(WeakReference(context.findActivity() as FragmentActivity))
    }
    DisposableEffect(context) {
        onDispose {
            biometricState.clearReferences()
        }
    }

    return biometricState
}