package com.davidluna.architectcoders2024.splash_ui.view

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
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
import com.davidluna.architectcoders2024.core_domain.core_entities.AppError
import com.davidluna.architectcoders2024.core_ui.R
import com.davidluna.architectcoders2024.core_ui.composables.ErrorDialogView
import com.davidluna.architectcoders2024.splash_ui.animation.AnimationLaunchedEffect
import com.davidluna.architectcoders2024.splash_ui.animation.AnimationState
import com.davidluna.architectcoders2024.splash_ui.animation.rememberAnimationState
import com.davidluna.architectcoders2024.splash_ui.biometrics.BioAuthState
import com.davidluna.architectcoders2024.splash_ui.biometrics.BiometricsLaunchedEffect
import com.davidluna.architectcoders2024.splash_ui.biometrics.rememberBiometricAuth
import com.davidluna.architectcoders2024.splash_ui.presenter.SplashEvent
import com.davidluna.architectcoders2024.splash_ui.presenter.SplashState
import com.piroworkz.composeandroidpermissions.PermissionLaunchedEffect
import com.piroworkz.composeandroidpermissions.PermissionsState.GRANTED
import com.piroworkz.composeandroidpermissions.PermissionsState.SHOULD_SHOW_RATIONALE
import com.piroworkz.composeandroidpermissions.rememberPermissionsState

@Composable
fun SplashScreen(
    state: SplashState,
    sendEvent: (SplashEvent) -> Unit
) {
    val permissions = rememberPermissionsState()
    val animationState = rememberAnimationState()
    val biometricAuthState: BioAuthState = rememberBiometricAuth()


    AnimationLaunchedEffect(animationState)

    if (animationState.currentState == AnimationState.FINISH) {
        PermissionLaunchedEffect(
            permissions,
            ACCESS_COARSE_LOCATION,
            ACCESS_FINE_LOCATION
        ) { sendEvent(SplashEvent.OnGranted) }
    }

    if (permissions.state == GRANTED) {
        BiometricsLaunchedEffect(biometricAuthState, state, sendEvent = { sendEvent(it) })
    }

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

        if (permissions.state == SHOULD_SHOW_RATIONALE && !permissions.requestedAtLeastOnce) {
            ErrorDialogView(
                error = AppError.Message(
                    0,
                    stringResource(R.string.permission_rationale_message)
                )
            ) {
                permissions.onDismissRationale()
            }
        }

        if (state.loading) {
            CircularProgressIndicator()
        }
    }

}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
private fun SplashScreenPreview() {
    com.davidluna.architectcoders2024.core_ui.theme.TmdbTheme {
        SplashScreen(
            state = SplashState(),
        ) {

        }
    }
}