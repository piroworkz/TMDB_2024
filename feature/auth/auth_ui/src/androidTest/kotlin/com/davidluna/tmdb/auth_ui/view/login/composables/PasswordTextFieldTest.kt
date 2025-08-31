package com.davidluna.tmdb.auth_ui.view.login.composables

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.isEditable
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextClearance
import androidx.compose.ui.test.performTextInput
import com.davidluna.tmdb.auth_domain.entities.TextInputError
import com.davidluna.tmdb.core_ui.theme.TmdbTheme
import org.junit.Rule
import org.junit.Test

class PasswordTextFieldTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun startsEmpty_noError_noToggleIcon(): Unit = composeTestRule.run {
        setContent()
        val textField = onNode(isEditable())

        textField.assertExists()
        onNodeWithContentDescription(Icons.Default.Visibility.name).assertDoesNotExist()
        onNodeWithContentDescription(Icons.Default.VisibilityOff.name).assertDoesNotExist()
        onNodeWithText(TextInputError.Required.message).assertDoesNotExist()
        onNodeWithText(TextInputError.InvalidLength(8).message).assertDoesNotExist()
    }

    @Test
    fun emptyPassword_showsRequiredError(): Unit = composeTestRule.run {
        setContent()
        val textField = onNode(isEditable())

        textField.performTextInput("12345678")
        textField.performTextClearance()
        onNodeWithText(TextInputError.Required.message).assertExists()
    }

    @Test
    fun shortPassword_showsInvalidLengthError(): Unit = composeTestRule.run {
        setContent()
        val textField = onNode(isEditable())

        textField.performTextInput("123")
        onNodeWithText(TextInputError.InvalidLength(8).message).assertExists()
    }

    @Test
    fun validPassword_noError_andToggleAppears(): Unit = composeTestRule.run {
        setContent()
        val textField = onNode(isEditable())

        textField.performTextInput("12345678")
        onNodeWithText(TextInputError.Required.message).assertDoesNotExist()
        onNodeWithText(TextInputError.InvalidLength(8).message).assertDoesNotExist()
        onNodeWithContentDescription(Icons.Default.VisibilityOff.name).assertExists()
    }

    @Test
    fun toggleVisibility_changesIcons(): Unit = composeTestRule.run {
        setContent()
        val textField = onNode(isEditable())

        textField.performTextInput("12345678")
        onNodeWithContentDescription(Icons.Default.VisibilityOff.name).assertExists().performClick()
        onNodeWithContentDescription(Icons.Default.Visibility.name).assertExists().performClick()
        onNodeWithContentDescription(Icons.Default.VisibilityOff.name).assertExists()
    }

    private fun ComposeContentTestRule.setContent() {
        setContent {
            var value by remember { mutableStateOf("") }
            var error: String? by remember { mutableStateOf(null) }

            fun validatePassword(text: String?): TextInputError? = when {
                text.isNullOrBlank() -> TextInputError.Required
                text.length < 8 -> TextInputError.InvalidLength(8)
                else -> null
            }

            TmdbTheme {
                PasswordTextField(
                    value = value,
                    fieldErrorMessage = error,
                    onValueChange = {
                        value = it
                        error = validatePassword(it)?.message
                    }
                )
            }
        }
    }
}