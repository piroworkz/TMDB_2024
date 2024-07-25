package com.davidluna.architectcoders2024.splash_ui.animation

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

@Composable
fun rememberAnimationState(
    animationState: MutableState<AnimationState> = remember { mutableStateOf(AnimationState.START) },
    scale: Animatable<Float, AnimationVector1D> = remember { Animatable(0f) },
    blur: Animatable<Float, AnimationVector1D> = remember { Animatable(0f) },
) = remember(animationState) {
    AnimationStateHolder(
        state = animationState,
        scale = scale,
        blur = blur
    )
}