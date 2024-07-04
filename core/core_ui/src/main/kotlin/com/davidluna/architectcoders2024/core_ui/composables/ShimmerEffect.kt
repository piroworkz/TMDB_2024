package com.davidluna.architectcoders2024.core_ui.composables

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.IntSize

fun Modifier.shimmer(enabled: Boolean, duration: Int = 1000): Modifier = composed {
    var intSize by remember { mutableStateOf(IntSize.Zero) }
    val transition = rememberInfiniteTransition(label = "shimmer")
    val startOffset by transition.animateFloat(
        initialValue = -2 * intSize.width.toFloat(),
        targetValue = 2 * intSize.width.toFloat(),
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = duration,
                easing = LinearEasing
            ),
            repeatMode = RepeatMode.Reverse
        ), label = "shimmer"
    )

    if (enabled) {
        background(getGrayGradientBrush(startOffset = startOffset, intSize = intSize))
            .onGloballyPositioned { coordinates ->
                intSize = coordinates.size
            }
    } else {
        background(appGradient())
    }

}


@Composable
private fun getGrayGradientBrush(startOffset: Float, intSize: IntSize): Brush =
    Brush.linearGradient(
        colors = listOf(
            colorScheme.primary,
            colorScheme.secondary,
            colorScheme.tertiary
        ),
        start = Offset(startOffset, 0f),
        end = Offset(startOffset + intSize.width.toFloat(), intSize.height.toFloat()),
        tileMode = TileMode.Clamp
    )