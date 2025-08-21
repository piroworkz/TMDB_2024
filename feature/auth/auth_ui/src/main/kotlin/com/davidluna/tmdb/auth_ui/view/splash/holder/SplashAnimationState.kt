package com.davidluna.tmdb.auth_ui.view.splash.holder

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun rememberSplashState(
    scope: CoroutineScope = rememberCoroutineScope(),
    onIdle: () -> Unit,
): SplashAnimationState = remember(scope) {
    SplashAnimationState(
        scope = scope,
        onIdle = onIdle
    )
}

class SplashAnimationState(
    private val scope: CoroutineScope,
    private val onIdle: () -> Unit,
) {
    val scale: MutableState<Animatable<Float, AnimationVector1D>> = mutableStateOf(Animatable(0f))
    val blur: MutableState<Animatable<Float, AnimationVector1D>> = mutableStateOf(Animatable(0f))

    fun startAnimation() {
        scope.launch {
            scale.value.animateTo(1f, animationSpec = tween(1000))
            blur.value.animateTo(100F, animationSpec = tween(300))
            blur.value.animateTo(0F, animationSpec = tween(700))
            this@SplashAnimationState.onAnimationFinished()
        }.invokeOnCompletion {
            if (it == null) {
                onAnimationFinished()
            }
        }
    }

    private fun onAnimationFinished() {
        scope.launch {
            scale.value.snapTo(1f)
            blur.value.snapTo(0f)
        }.invokeOnCompletion {
            if (it == null) {
                onIdle()
            }
        }
    }
}

operator fun MutableState<Animatable<Float, AnimationVector1D>>.invoke(): Float {
    return value.value
}