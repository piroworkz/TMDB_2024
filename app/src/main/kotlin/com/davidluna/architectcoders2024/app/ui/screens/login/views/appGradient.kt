package com.davidluna.architectcoders2024.app.ui.screens.login.views

import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Brush

@Composable
fun appGradient() = Brush.linearGradient(
    colors = listOf(
        colorScheme.primary,
        colorScheme.secondary,
        colorScheme.tertiary
    ),
)