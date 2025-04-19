package com.davidluna.tmdb.splash.view

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.graphics.Shader
import android.os.Build
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter.Companion.tint
import androidx.compose.ui.graphics.GraphicsLayerScope
import androidx.compose.ui.graphics.asComposeRenderEffect
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.davidluna.tmdb.core_domain.entities.errors.AppError
import com.davidluna.tmdb.core_domain.entities.errors.AppErrorCode
import com.davidluna.tmdb.core_ui.R
import com.davidluna.tmdb.core_ui.composables.ErrorDialogView
import com.davidluna.tmdb.core_ui.theme.TmdbTheme
import com.davidluna.tmdb.splash.animation.AnimationLaunchedEffect
import com.davidluna.tmdb.splash.animation.AnimationState
import com.davidluna.tmdb.splash.animation.AnimationStateHolder
import com.davidluna.tmdb.splash.animation.rememberAnimationState
import com.piroworkz.composeandroidpermissions.PermissionLaunchedEffect
import com.piroworkz.composeandroidpermissions.PermissionsState.SHOULD_SHOW_RATIONALE
import com.piroworkz.composeandroidpermissions.rememberPermissionsState

@Composable
fun SplashScreen(
    onGranted: () -> Unit,
) {
    val permissions = rememberPermissionsState()
    val animationState = rememberAnimationState()

    AnimationLaunchedEffect(animationState)

    if (animationState.currentState == AnimationState.FINISH) {
        PermissionLaunchedEffect(
            permissions,
            ACCESS_COARSE_LOCATION,
            ACCESS_FINE_LOCATION
        ) { onGranted() }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        Image(
            painter = painterResource(id = R.drawable.logo_v1),
            contentDescription = "Logo",
            modifier = Modifier
                .graphicsLayer {
                    scaleX = animationState.currentScale
                    scaleY = animationState.currentScale
                    createBlurEffect(animationState)
                },
            colorFilter = tint(colorScheme.onPrimary.copy(alpha = 0.5f))
        )

        if (permissions.state == SHOULD_SHOW_RATIONALE && !permissions.requestedAtLeastOnce) {
            ErrorDialogView(
                error = AppError.Message(
                    AppErrorCode.UNKNOWN,
                    stringResource(R.string.permission_rationale_message)
                )
            ) {
                permissions.onDismissRationale()
            }
        }
    }

}

private fun GraphicsLayerScope.createBlurEffect(animationState: AnimationStateHolder) {
    if (animationState.currentBlur > 0) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            renderEffect = android.graphics.RenderEffect.createBlurEffect(
                animationState.currentBlur,
                animationState.currentBlur,
                Shader.TileMode.DECAL
            )
                .asComposeRenderEffect()
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
private fun SplashScreenPreview() {
    TmdbTheme {
        SplashScreen { }
    }
}

