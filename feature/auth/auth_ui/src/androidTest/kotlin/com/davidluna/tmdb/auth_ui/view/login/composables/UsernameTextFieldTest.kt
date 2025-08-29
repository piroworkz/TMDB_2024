package com.davidluna.tmdb.auth_ui.view.login.composables

import android.util.Patterns
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
import androidx.compose.ui.test.printToLog
import com.davidluna.tmdb.auth_domain.entities.TextInputError
import com.davidluna.tmdb.core_ui.theme.TmdbTheme
import org.junit.Rule
import org.junit.Test

class UsernameTextFieldTest {

    @get:Rule
    val composeTestRule: ComposeContentTestRule = createComposeRule()

    @Test
    fun startsEmpty_noError_noClearButton(): Unit = composeTestRule.run {
        setContent()
        val textField = onNode(isEditable())

        textField.assertExists()
        onNodeWithContentDescription("Filled.Clear").assertDoesNotExist()
        onNodeWithText(TextInputError.Required.message).assertDoesNotExist()
        onNodeWithText(TextInputError.InvalidEmail.message).assertDoesNotExist()
    }

    @Test
    fun typingInvalidEmail_showsInvalidEmailError(): Unit = composeTestRule.run {
        setContent()
        val textField = onNode(isEditable())

        textField.performTextInput("davidluna")
        onNodeWithText(TextInputError.InvalidEmail.message).assertExists().printToLog("<-- after input")
    }

    @Test
    fun typingValidEmail_noError(): Unit = composeTestRule.run {
        setContent()
        val textField = onNode(isEditable())

        textField.performTextInput("davidluna@mail.com")
        onNodeWithText(TextInputError.InvalidEmail.message).assertDoesNotExist()
        onNodeWithText(TextInputError.Required.message).assertDoesNotExist()
    }

    @Test
    fun clearingText_showsRequiredError(): Unit = composeTestRule.run {
        setContent()
        val textField = onNode(isEditable())

        textField.performTextInput("davidluna@mail.com")
        textField.performTextClearance()
        onNodeWithText(TextInputError.Required.message).assertExists()
    }

    @Test
    fun clearButton_clearsText(): Unit = composeTestRule.run {
        setContent()
        val textField = onNode(isEditable())

        textField.performTextInput("abc")
        onNodeWithContentDescription("Filled.Clear").assertExists().performClick()
        onNodeWithText(TextInputError.Required.message).assertExists()
    }

    private fun ComposeContentTestRule.setContent() {
        setContent {
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
    }

    fun validateUsername(text: String?): TextInputError? = when {
        text.isNullOrBlank() -> TextInputError.Required
        !Patterns.EMAIL_ADDRESS.toRegex().matches(text) -> TextInputError.InvalidEmail
        else -> null
    }
}
