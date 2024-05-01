package com.davidluna.architectcoders2024.app.ui.screens.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.ColorFilter.Companion.tint
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.davidluna.architectcoders2024.R
import com.davidluna.architectcoders2024.app.ui.composables.ErrorDialogView
import com.davidluna.architectcoders2024.app.ui.screens.biometrics.rememberBiometricAuth
import com.davidluna.architectcoders2024.app.ui.screens.splash.animation.rememberAnimationState
import com.davidluna.architectcoders2024.app.ui.screens.splash.permissions.PermissionState.SHOULD_SHOW_RATIONALE
import com.davidluna.architectcoders2024.app.ui.screens.splash.permissions.rememberPermissionState
import com.davidluna.architectcoders2024.app.ui.screens.splash.views.AnimationLaunchedEffect
import com.davidluna.architectcoders2024.app.ui.screens.splash.views.BiometricsLaunchedEffect
import com.davidluna.architectcoders2024.app.ui.screens.splash.views.PermissionLaunchedEffect
import com.davidluna.architectcoders2024.app.ui.theme.TmdbTheme
import com.davidluna.architectcoders2024.domain.AppError

@Composable
fun SplashScreen(
    state: SplashViewModel.State,
    sendEvent: (SplashEvent) -> Unit
) {
    val permissionState = rememberPermissionState()
    val animationState = rememberAnimationState()
    val biometricAuthState = rememberBiometricAuth()

    BiometricsLaunchedEffect(biometricAuthState, state, sendEvent = { sendEvent(it) })
    AnimationLaunchedEffect(animationState)
    PermissionLaunchedEffect(permissionState) { sendEvent(SplashEvent.OnGranted) }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Image(
            painter = painterResource(id = R.drawable.logo_v1),
            contentDescription = "Logo",
            modifier = Modifier
                .scale(animationState.currentScale)
                .blur(animationState.currentBlur.dp)
                .fillMaxSize(),
            colorFilter = tint(colorScheme.onPrimary.copy(alpha = 0.5f))
        )

        if (permissionState.currentState == SHOULD_SHOW_RATIONALE && !permissionState.requestedAtLeastOnce) {
            ErrorDialogView(
                error = AppError.Unknown(
                    0,
                    stringResource(R.string.permission_rationale_message),
                    false
                )
            ) {
                permissionState.onDismissRationale()
            }
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
        ) {

        }
    }
}