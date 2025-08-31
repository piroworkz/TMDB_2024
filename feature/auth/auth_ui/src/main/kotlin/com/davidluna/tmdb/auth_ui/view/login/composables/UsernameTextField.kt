package com.davidluna.tmdb.auth_ui.view.login.composables

import android.util.Patterns
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import com.davidluna.tmdb.auth_domain.entities.TextInputError
import com.davidluna.tmdb.core_ui.R
import com.davidluna.tmdb.core_ui.theme.TmdbTheme
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
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = Icons.Default.Person.name
            )
        },
        trailingIcon = {
            AnimatedVisibility(value.isNotEmpty()) {
                IconButton(
                    onClick = { onValueChange("") },
                    content = {
                        Icon(
                            imageVector = Icons.Default.Clear,
                            contentDescription = Icons.Default.Clear.name
                        )
                    }
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

@Preview(showBackground = true)
@Composable
private fun UsernameTextFieldPreview() {

    fun validateUsername(text: String?): TextInputError? = when {
        text.isNullOrBlank() -> TextInputError.Required
        !Patterns.EMAIL_ADDRESS.toRegex().matches(text) -> TextInputError.InvalidEmail
        else -> null
    }

    var value by remember { mutableStateOf("") }
    var error: String? by remember { mutableStateOf(null) }
    TmdbTheme {
        UsernameTextField(
            value = value,
            fieldErrorMessage = error,
            onValueChange = {
                value = it
                error = validateUsername(it)?.message
            }
        )
    }

}