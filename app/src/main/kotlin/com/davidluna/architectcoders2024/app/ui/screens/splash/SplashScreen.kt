package com.davidluna.architectcoders2024.app.ui.screens.splash

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
import com.davidluna.architectcoders2024.R
import com.davidluna.architectcoders2024.app.ui.composables.ErrorDialogView
import com.davidluna.architectcoders2024.app.ui.screens.biometrics.BioAuthState
import com.davidluna.architectcoders2024.app.ui.screens.biometrics.rememberBiometricAuth
import com.davidluna.architectcoders2024.app.ui.screens.splash.animation.rememberAnimationState
import com.davidluna.architectcoders2024.app.ui.screens.splash.views.AnimationLaunchedEffect
import com.davidluna.architectcoders2024.app.ui.screens.splash.views.BiometricsLaunchedEffect
import com.davidluna.architectcoders2024.app.ui.theme.TmdbTheme
import com.davidluna.architectcoders2024.domain.AppError
import com.piroworkz.composeandroidpermissions.PermissionLaunchedEffect
import com.piroworkz.composeandroidpermissions.PermissionsState.SHOULD_SHOW_RATIONALE
import com.piroworkz.composeandroidpermissions.rememberPermissionsState

@Composable
fun SplashScreen(
    state: SplashViewModel.State,
    sendEvent: (SplashEvent) -> Unit
) {
    val permissions = rememberPermissionsState()
    val animationState = rememberAnimationState()
    val biometricAuthState: BioAuthState = rememberBiometricAuth()

    BiometricsLaunchedEffect(biometricAuthState, state, sendEvent = { sendEvent(it) })
    AnimationLaunchedEffect(animationState)
    PermissionLaunchedEffect(
        permissions,
        ACCESS_COARSE_LOCATION,
        ACCESS_FINE_LOCATION
    ) { sendEvent(SplashEvent.OnGranted) }

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
                error = AppError.Unknown(
                    0,
                    stringResource(R.string.permission_rationale_message),
                    false
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
    TmdbTheme {
        SplashScreen(
            state = SplashViewModel.State(),
        ) {

        }
    }
}