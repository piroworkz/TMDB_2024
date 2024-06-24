package com.davidluna.architectcoders2024.media_ui.view.details.composables

import androidx.compose.material3.CardColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun cardColors() = CardColors(
    containerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.2f),
    contentColor = MaterialTheme.colorScheme.onPrimary,
    disabledContainerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.2f),
    disabledContentColor = MaterialTheme.colorScheme.onPrimary
)