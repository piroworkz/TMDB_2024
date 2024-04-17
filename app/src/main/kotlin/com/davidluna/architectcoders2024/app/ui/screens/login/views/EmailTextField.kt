package com.davidluna.architectcoders2024.app.ui.screens.login.views

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun EmailTextField(
    email: String?,
    onValueChanged: (String) -> Unit = {}
) {

    OutlinedTextField(
        value = email ?: "",
        onValueChange = { onValueChanged(it) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 32.dp),
        label = {
            Text(
                text = "Email",
                color = colorScheme.primary.copy(alpha = 0.5f)
            )
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Email,
                contentDescription = Icons.Default.Email.name
            )
        },
        trailingIcon = {
            if (!email.isNullOrEmpty()) {
                IconButton(onClick = { onValueChanged(String()) }) {
                    Icon(
                        imageVector = Icons.Default.Clear,
                        contentDescription = Icons.Default.Clear.name
                    )
                }
            }
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Next
        ),
        maxLines = 1,
        colors = textFieldColors()
    )
}
