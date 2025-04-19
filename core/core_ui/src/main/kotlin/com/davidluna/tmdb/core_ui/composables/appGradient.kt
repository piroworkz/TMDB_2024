package com.davidluna.tmdb.core_ui.composables

import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush

@Composable
fun appGradient() = Brush.linearGradient(
    colors = listOf(
        colorScheme.primary,
        colorScheme.secondary,
        colorScheme.tertiary
    ),
)

@Composable
fun errorGradient(modifier: Modifier = Modifier): Brush {
   return Brush.linearGradient(
        colors = listOf(
            colorScheme.error,
            colorScheme.onError,
            colorScheme.error,
        ),
    )
}