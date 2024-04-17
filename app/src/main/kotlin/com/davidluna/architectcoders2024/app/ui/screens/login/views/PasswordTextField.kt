package com.davidluna.architectcoders2024.app.ui.screens.login.views

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun PasswordTextField(
    password: String?,
    onPasswordChanged: (String) -> Unit = {},
) {
    var showPassword by remember { mutableStateOf(false) }
    OutlinedTextField(
        value = password ?: "",
        onValueChange = { onPasswordChanged(it) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 32.dp),
        label = {
            Text(
                text = "Password",
                color = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f)
            )
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Outlined.Lock,
                contentDescription = Icons.Outlined.Lock.name
            )
        },
        trailingIcon = {
            PasswordVisibilityIcon(
                password = password ?: "",
                showPassword = showPassword,
                onToggle = { showPassword = !showPassword }
            )
        },
        visualTransformation = passwordVisualTransformation(showPassword),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Next
        ),
        maxLines = 1,
        colors = textFieldColors()
    )
}


@Composable
private fun PasswordVisibilityIcon(
    password: String,
    showPassword: Boolean = false,
    onToggle: () -> Unit = {}
) {

    if (password.isNotEmpty()) {
        IconButton(onClick = { onToggle() }) {
            if (showPassword) {
                Icon(
                    imageVector = Icons.Outlined.VisibilityOff,
                    contentDescription = Icons.Outlined.VisibilityOff.name
                )
            } else {
                Icon(
                    imageVector = Icons.Outlined.Visibility,
                    contentDescription = Icons.Outlined.Visibility.name
                )
            }
        }
    }
}

@Composable
private fun passwordVisualTransformation(show: Boolean) = if (show) {
    VisualTransformation.None
} else {
    PasswordVisualTransformation()
}

