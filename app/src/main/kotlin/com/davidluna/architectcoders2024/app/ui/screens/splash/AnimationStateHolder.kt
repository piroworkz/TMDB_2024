package com.davidluna.architectcoders2024.app.ui.screens.splash

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

class AnimationStateHolder(
    private val state: MutableState<AnimationState>,
    private val scale: Animatable<Float, AnimationVector1D>,
    private val blur: Animatable<Float, AnimationVector1D>
) {

    val currentState: AnimationState
        get() = state.value

    val currentScale: Float
        get() = scale.value

    val currentBlur: Float
        get() = blur.value


    suspend fun observeState() {
        when (currentState) {

            AnimationState.FINISH -> {
                scale.snapTo(1f)
                blur.snapTo(0f)
            }

            AnimationState.START -> {
                scale.animateTo(1f, animationSpec = tween(1000))
                blur.animateTo(100F, animationSpec = tween(300))
                blur.animateTo(0F, animationSpec = tween(700))
                this.state.value = AnimationState.FINISH
            }
        }
    }

}


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