package com.davidluna.tmdb.auth_ui.view.login.composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import com.davidluna.tmdb.core_ui.R
import com.davidluna.tmdb.core_ui.theme.dimens.Dimens

@Composable
fun UsernameTextField(
    value: String,
    fieldErrorMessage: String?,
    onValueChange: (String) -> Unit,
) {
    OutlinedTextField(
        value = value,
        onValueChange = { onValueChange(it) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = Dimens.margins.large),
        label = { Text(stringResource(R.string.label_username)) },
        placeholder = { Text(stringResource(R.string.label_username)) },
        leadingIcon = { Icon(Icons.Default.Person, null) },
        trailingIcon = {
            AnimatedVisibility(value.isNotEmpty()) {
                IconButton(
                    onClick = { onValueChange("") },
                    content = { Icon(Icons.Default.Clear, null) }
                )
            }
        },
        supportingText = {
            if (!fieldErrorMessage.isNullOrBlank()) {
                Text(
                    text = fieldErrorMessage,
                    color = colorScheme.error
                )
            }
        },
        isError = fieldErrorMessage.isNullOrBlank().not(),
        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
        singleLine = true,
        colors = OutlinedTextFieldDefaults.colors().copy(
            focusedIndicatorColor = colorScheme.primary,
            unfocusedIndicatorColor = colorScheme.primary
        )
    )
}