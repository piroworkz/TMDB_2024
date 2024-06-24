package com.davidluna.architectcoders2024.splash_ui.animation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.davidluna.architectcoders2024.splash_ui.animation.AnimationStateHolder

@Composable
fun AnimationLaunchedEffect(animationState: AnimationStateHolder) {
    LaunchedEffect(key1 = animationState.currentState) {
        animationState.observeState()
    }
}