package com.davidluna.tmdb.auth_ui.view.login.composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.davidluna.tmdb.core_ui.R
import com.davidluna.tmdb.core_ui.theme.dimens.Dimens
import com.davidluna.tmdb.core_ui.utils.toggle

@Composable
fun PasswordTextField(
    value: String,
    fieldErrorMessage: String?,
    onValueChange: (String) -> Unit,
) {
    var showPassword by remember { mutableStateOf(false) }

    OutlinedTextField(
        value = value,
        onValueChange = { onValueChange(it) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = Dimens.margins.large),
        label = { Text(stringResource(R.string.label_password)) },
        placeholder = { Text(stringResource(R.string.label_password)) },
        leadingIcon = { Icon(Icons.Default.Lock, null) },
        trailingIcon = { PasswordTrailingIcon(value, showPassword) { showPassword = it } },
        supportingText = {
            if (!fieldErrorMessage.isNullOrBlank()) {
                Text(
                    text = fieldErrorMessage,
                    color = colorScheme.error
                )
            }
        },
        isError = !fieldErrorMessage.isNullOrBlank(),
        singleLine = true,
        visualTransformation = if (!showPassword) PasswordVisualTransformation() else VisualTransformation.None,
        colors = OutlinedTextFieldDefaults.colors().copy(
            focusedIndicatorColor = colorScheme.primary,
            unfocusedIndicatorColor = colorScheme.primary
        )
    )
}

@Composable
private fun PasswordTrailingIcon(
    password: String,
    showPassword: Boolean,
    togglePassword: (Boolean) -> Unit,
) {
    AnimatedVisibility(password.isNotEmpty()) {
        IconButton(
            onClick = { togglePassword(showPassword.toggle()) },
            content = {
                if (showPassword) {
                    Icon(Icons.Default.Visibility, null)
                } else {
                    Icon(Icons.Default.VisibilityOff, null)
                }
            }
        )
    }
}