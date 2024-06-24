package com.davidluna.architectcoders2024.splash_ui.animation

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.tween
import androidx.compose.runtime.MutableState

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

