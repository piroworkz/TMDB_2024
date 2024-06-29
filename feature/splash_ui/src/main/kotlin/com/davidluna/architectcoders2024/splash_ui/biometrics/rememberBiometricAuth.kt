package com.davidluna.architectcoders2024.splash_ui.biometrics

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.fragment.app.FragmentActivity
import com.davidluna.architectcoders2024.core_ui.composables.findActivity

@Composable
fun rememberBiometricAuth(
    context: Context = LocalContext.current,
    state: MutableState<BiometricState> = remember { mutableStateOf(BiometricState.UNAVAILABLE) }
): BiometricAuthenticationState =
    remember(context, state) {
        BiometricAuthenticationState((context.findActivity() as FragmentActivity), state)
    }


