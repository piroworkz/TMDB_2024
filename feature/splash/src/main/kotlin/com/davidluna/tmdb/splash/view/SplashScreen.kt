package com.davidluna.tmdb.splash.view

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.graphics.Shader
import android.os.Build
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter.Companion.tint
import androidx.compose.ui.graphics.GraphicsLayerScope
import androidx.compose.ui.graphics.asComposeRenderEffect
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.davidluna.tmdb.core_domain.entities.errors.AppError
import com.davidluna.tmdb.core_domain.entities.errors.AppErrorCode
import com.davidluna.tmdb.core_domain.entities.tags.CoreTag
import com.davidluna.tmdb.core_ui.R
import com.davidluna.tmdb.core_ui.composables.ErrorDialogView
import com.davidluna.tmdb.core_ui.theme.TmdbTheme
import com.davidluna.tmdb.core_ui.theme.dimens.Dimens
import com.davidluna.tmdb.splash.presenter.SplashEvent
import com.davidluna.tmdb.splash.presenter.SplashEvent.LaunchBioPrompt
import com.davidluna.tmdb.splash.presenter.SplashViewModel
import com.davidluna.tmdb.splash.view.animation.AnimationLaunchedEffect
import com.davidluna.tmdb.splash.view.animation.AnimationState
import com.davidluna.tmdb.splash.view.animation.AnimationStateHolder
import com.davidluna.tmdb.splash.view.animation.rememberAnimationState
import com.davidluna.tmdb.splash.view.biometrics.rememberBiometricAuth
import com.piroworkz.composeandroidpermissions.PermissionLaunchedEffect
import com.piroworkz.composeandroidpermissions.PermissionsState.SHOULD_SHOW_RATIONALE
import com.piroworkz.composeandroidpermissions.rememberPermissionsState

@Composable
fun SplashScreen(
    state: SplashViewModel.State,
    sendEvent: (event: SplashEvent) -> Unit
) {
    val permissions = rememberPermissionsState()
    val animationState = rememberAnimationState()
    val bioState = rememberBiometricAuth(sendEvent = { sendEvent(it) })

    AnimationLaunchedEffect(animationState)

    if (animationState.currentState == AnimationState.FINISH) {
        PermissionLaunchedEffect(
            permissions,
            ACCESS_COARSE_LOCATION,
            ACCESS_FINE_LOCATION
        ) { sendEvent(SplashEvent.OnPermissionsGranted) }
    }

    if (state.permissionGranted) {
        bioState.LaunchEffects(
            session = state.session,
            result = state.bioResult,
            launchBioPrompt = state.launchBioPrompt
        )
    }

    Box(
        modifier = Modifier.fillMaxSize()
            .testTag(CoreTag.SPLASH_SCREEN_VIEW),
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
                }
                .testTag(CoreTag.SPLASH_TMDB_LOGO),
            colorFilter = tint(colorScheme.onPrimary.copy(alpha = 0.5f))
        )

        AnimatedVisibility(!state.launchBioPrompt,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(bottom = Dimens.margins.xLarge)) {
            TextButton(
                onClick = { sendEvent(LaunchBioPrompt(true)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .padding(bottom = Dimens.margins.xLarge)
            ) {
                Text(
                    text = stringResource(id = R.string.btn_launch_biometrics),
                    modifier = Modifier.padding(horizontal = Dimens.margins.xLarge)
                )
            }
        }

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
        SplashScreen(
            state = SplashViewModel.State(),
            sendEvent = {}
        )
    }
}

