package com.davidluna.tmdb.core_ui.composables

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember

@Composable
fun SlideFromTopAnimation(
    target: Boolean,
    label: String,
    duration: MutableIntState = remember { mutableIntStateOf(500) },
    content: @Composable (Boolean) -> Unit
) {

    AnimatedContent(
        targetState = target,
        transitionSpec = { contentTransform(duration) },
        label = label
    ) {
        content(it)
    }
}

private fun AnimatedContentTransitionScope<Boolean>.contentTransform(
    duration: MutableIntState
) = (
        fadeIn(
            animationSpec = tween(duration.intValue)
        ) + slideIntoContainer(
            AnimatedContentTransitionScope.SlideDirection.Down,
            animationSpec = tween(duration.intValue)
        )
        )
    .togetherWith(
        fadeOut(tween(duration.intValue))
    )