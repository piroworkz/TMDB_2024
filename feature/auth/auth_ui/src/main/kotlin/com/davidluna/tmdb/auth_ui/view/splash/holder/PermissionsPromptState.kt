package com.davidluna.tmdb.auth_ui.view.splash.holder

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.Manifest.permission.POST_NOTIFICATIONS
import android.app.Activity
import android.content.pm.PackageManager
import android.os.Build
import androidx.activity.compose.LocalActivityResultRegistryOwner
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.ActivityResultRegistryOwner
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.util.UUID

@Composable
fun rememberPermissionsPromptState(
    onPermissionsResults: (Map<String, @JvmSuppressWildcards Boolean>) -> Unit,
): PermissionsPromptState {
    val registryOwner = LocalActivityResultRegistryOwner.current
    return remember(registryOwner) {
        PermissionsPromptState(
            registryOwner = registryOwner,
            onPermissionsResults = { map -> onPermissionsResults(map) }
        )
    }
}

class PermissionsPromptState(
    private val registryOwner: ActivityResultRegistryOwner?,
    private val onPermissionsResults: (Map<String, @JvmSuppressWildcards Boolean>) -> Unit,
) {

    private val permissions: Array<String> = buildPermissionsArray()
    private val launcher: ActivityResultLauncher<Array<String>>? by lazy { buildLauncher() }

    operator fun invoke() {
        if (areGranted()) {
            onPermissionsResults(permissions.associateWith { true })
        } else {
            launcher?.launch(permissions)
        }
    }

    fun areGranted(): Boolean {
        return permissions.any { permission: String ->
            (registryOwner as? Activity)?.let {
                ContextCompat.checkSelfPermission(
                    it,
                    permission
                ) == PackageManager.PERMISSION_GRANTED
            } ?: false
        }
    }

    private fun arePermanentlyDenied(): Boolean =
        (registryOwner as? Activity)?.let { currentActivity: Activity ->
            permissions.any { permission ->
                !ActivityCompat.shouldShowRequestPermissionRationale(currentActivity, permission)
            }
        } ?: false

    private fun buildPermissionsArray(): Array<String> {
        val locationPermissions = arrayOf(ACCESS_COARSE_LOCATION, ACCESS_FINE_LOCATION)
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            locationPermissions + POST_NOTIFICATIONS
        } else {
            locationPermissions
        }
    }

    private fun buildLauncher(): ActivityResultLauncher<Array<String>>? =
        registryOwner?.activityResultRegistry?.register(
            key = UUID.randomUUID().toString(),
            contract = ActivityResultContracts.RequestMultiplePermissions(),
            callback = { map ->
                if (arePermanentlyDenied()) {
                    onPermissionsResults(permissions.associateWith { false })
                } else {
                    onPermissionsResults(map)
                }
            }
        )
}