package com.davidluna.architectcoders2024.app.ui.screens.splash

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.ColorFilter.Companion.tint
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.davidluna.architectcoders2024.R
import com.davidluna.architectcoders2024.app.ui.theme.TmdbTheme

@Composable
fun SplashScreen(
    animationState: (AnimationState) -> Unit
) {

    val scale = remember { Animatable(0f) }
    val blur = remember { Animatable(0f) }

    LaunchedEffect(key1 = Unit) {
        scale.animateTo(1f, animationSpec = tween(1500))
        blur.animateTo(100F, animationSpec = tween(300))
        blur.animateTo(0F, animationSpec = tween(200))
        animationState(AnimationState.FINISH)
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.logo_v1),
            contentDescription = "Logo",
            modifier = Modifier
                .scale(scale.value)
                .blur(blur.value.dp)
                .fillMaxSize(),
            colorFilter = tint(colorScheme.onPrimary.copy(alpha = 0.5f))
        )
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