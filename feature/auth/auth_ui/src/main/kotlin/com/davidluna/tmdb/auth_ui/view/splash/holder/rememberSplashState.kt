package com.davidluna.tmdb.auth_ui.view.splash.holder

import android.app.Activity
import androidx.activity.compose.LocalActivity
import androidx.activity.compose.LocalActivityResultRegistryOwner
import androidx.activity.result.ActivityResultRegistry
import androidx.activity.result.ActivityResultRegistryOwner
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.fragment.app.FragmentActivity
import com.davidluna.tmdb.core_ui.navigation.Destination
import kotlinx.coroutines.CoroutineScope

@Composable
fun rememberSplashState(
    activity: Activity? = LocalActivity.current,
    registryOwner: ActivityResultRegistryOwner? = LocalActivityResultRegistryOwner.current,
    activityResultRegistry: ActivityResultRegistry? = registryOwner?.activityResultRegistry,
    scope: CoroutineScope = rememberCoroutineScope(),
    onPermissionsResults: () -> Unit,
    navigateHome: () -> Unit,
    navigate: (Destination) -> Unit,
): SplashStateHolder {
    val biometricAuthenticator =
        remember(registryOwner) {
            BiometricAuthenticator(
                activity = registryOwner as? FragmentActivity,
                navigateHome = navigateHome,
                navigate = navigate
            )
        }
    return remember(activity) {
        SplashStateHolder(
            activity = activity,
            activityResultRegistry = activityResultRegistry,
            biometricAuthenticator = biometricAuthenticator,
            scope = scope,
            onPermissionsResults = { onPermissionsResults() }
        )
    }
}


operator fun MutableState<Animatable<Float, AnimationVector1D>>.invoke(): Float {
    return value.value
}