package com.davidluna.tmdb.auth_ui.view.splash.holder

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.app.Activity
import android.content.pm.PackageManager
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.ActivityResultRegistry
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.tween
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.util.UUID

class SplashStateHolder(
    activityResultRegistry: ActivityResultRegistry?,
    private val biometricAuthenticator: BiometricAuthenticator,
    private val activity: Activity?,
    private val scope: CoroutineScope,
    private val onPermissionsResults: () -> Unit,
) {
    private val locationPermissions = arrayOf(ACCESS_COARSE_LOCATION, ACCESS_FINE_LOCATION)
    private val launcher: ActivityResultLauncher<Array<String>>? = activityResultRegistry?.register(
        key = UUID.randomUUID().toString(),
        contract = ActivityResultContracts.RequestMultiplePermissions(),
        callback = { map -> onPermissionsResults(map) }
    )

    val scale: MutableState<Animatable<Float, AnimationVector1D>> = mutableStateOf(Animatable(0f))
    val blur: MutableState<Animatable<Float, AnimationVector1D>> = mutableStateOf(Animatable(0f))

    fun startAnimation() {
        scope.launch {
            scale.value.animateTo(1f, animationSpec = tween(1000))
            blur.value.animateTo(100F, animationSpec = tween(300))
            blur.value.animateTo(0F, animationSpec = tween(700))
            onAnimationFinished()
        }
    }

    private fun onAnimationFinished() {
        scope.launch {
            scale.value.snapTo(1f)
            blur.value.snapTo(0f)
        }
        if (!validateLocationPermissions()) {
            launcher?.launch(locationPermissions)
        } else {
            onPermissionsResults()
        }
    }

    fun authenticate() {
        biometricAuthenticator.showBiometricPrompt()
    }

    private fun onPermissionsResults(map: Map<String, @JvmSuppressWildcards Boolean>) {
        when {
            arePermanentlyDenied(activity, map) -> {
                onPermissionsResults()
                launcher?.unregister()
            }

            map.all { it.value } -> {
                onPermissionsResults()
                launcher?.unregister()
            }

            else -> onPermissionsResults()
        }
    }

    private fun arePermanentlyDenied(
        activity: Activity?,
        map: Map<String, @JvmSuppressWildcards Boolean>,
    ): Boolean = activity?.let { currentActivity: Activity ->
        map.filterValues { !it }.keys.any { permission ->
            !ActivityCompat.shouldShowRequestPermissionRationale(currentActivity, permission)
        }
    } ?: false

    private fun validateLocationPermissions(): Boolean =
        locationPermissions.any { permission: String ->
            activity?.let {
                ContextCompat.checkSelfPermission(
                    it,
                    permission
                ) == PackageManager.PERMISSION_GRANTED
            } ?: false
        }
}