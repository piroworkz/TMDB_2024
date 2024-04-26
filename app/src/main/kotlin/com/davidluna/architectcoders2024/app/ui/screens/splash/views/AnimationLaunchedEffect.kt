package com.davidluna.architectcoders2024.app.ui.screens.splash.views

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.davidluna.architectcoders2024.app.ui.screens.animation.AnimationStateHolder

@Composable
fun AnimationLaunchedEffect(animationState: AnimationStateHolder) {
    LaunchedEffect(key1 = animationState.currentState) {
        animationState.observeState()
    }
}