package com.davidluna.tmdb.splash.view.animation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect

@Composable
fun AnimationLaunchedEffect(animationState: AnimationStateHolder) {
    LaunchedEffect(key1 = animationState.currentState) {
        animationState.observeState()
    }
}