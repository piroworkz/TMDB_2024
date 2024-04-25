package com.davidluna.architectcoders2024.app.ui.screens.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.ColorFilter.Companion.tint
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.davidluna.architectcoders2024.R
import com.davidluna.architectcoders2024.app.ui.common.ErrorDialogView
import com.davidluna.architectcoders2024.app.ui.screens.splash.PermissionState.DENIED
import com.davidluna.architectcoders2024.app.ui.screens.splash.PermissionState.GRANTED
import com.davidluna.architectcoders2024.app.ui.screens.splash.PermissionState.SHOULD_SHOW_RATIONALE
import com.davidluna.architectcoders2024.app.ui.theme.TmdbTheme
import com.davidluna.architectcoders2024.app.utils.log
import com.davidluna.architectcoders2024.domain.AppError

@Composable
fun SplashScreen(
    onGranted: () -> Unit
) {

    val permissionState = rememberPermissionState()
    val animationState = rememberAnimationState()

    LaunchedEffect(key1 = permissionState.currentState) {
        "Permission state: ${permissionState.currentState}".log()
        when (permissionState.currentState) {
            GRANTED -> {
                "Permissions were granted".log()
                onGranted()
            }

            DENIED -> {
                "Permissions were denied for good".log()
                permissionState.requestPermission()
            }

            SHOULD_SHOW_RATIONALE -> {
                "Permissions were denied. Should show rationale".log()
            }
        }
    }

    LaunchedEffect(key1 = animationState.currentState) {
        animationState.observeState()
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

        if (permissionState.currentState == SHOULD_SHOW_RATIONALE && !permissionState.requestedAtLeastOnce) {
            ErrorDialogView(
                error = AppError.Unknown(
                    0,
                    "The location permission was denied, this permission is required, you should consider granting it.",
                    false
                )
            ) {
                permissionState.onDismissRationale()
            }
        }
    }

}


enum class AnimationState {
    FINISH, START
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
private fun SplashScreenPreview() {
    TmdbTheme {
        SplashScreen {

        }
    }
}