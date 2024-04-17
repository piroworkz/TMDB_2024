package com.davidluna.architectcoders2024.app.ui.screens.login.views

import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.Composable

@Composable
fun textFieldColors() = OutlinedTextFieldDefaults.colors(
    disabledContainerColor = colorScheme.secondary.copy(alpha = 0.5f),
    focusedContainerColor = colorScheme.secondary.copy(alpha = 0.5f),
    unfocusedContainerColor = colorScheme.secondary.copy(alpha = 0.5f),
    errorContainerColor = colorScheme.secondary.copy(alpha = 0.5f),
    focusedBorderColor = colorScheme.onPrimary,
    unfocusedBorderColor = colorScheme.onPrimary,
    disabledBorderColor = colorScheme.onPrimary
)