package com.davidluna.tmdb.auth_ui.view.login

import androidx.compose.ui.semantics.ProgressBarRangeInfo
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.hasProgressBarRangeInfo
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.isEditable
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import com.davidluna.tmdb.auth_ui.presenter.login.LoginEvent
import com.davidluna.tmdb.auth_ui.presenter.login.LoginViewModel
import com.davidluna.tmdb.core_domain.entities.AppError
import com.davidluna.tmdb.core_domain.entities.AppErrorCode
import com.davidluna.tmdb.core_ui.theme.TmdbTheme
import org.junit.Rule
import org.junit.Test

class LoginScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun initialRender_showsFieldsAndButtons(): Unit = composeTestRule.run {
        setContentWithState(LoginViewModel.State())

        onAllNodes(isEditable()).assertCountEquals(2)
        onNodeWithText("Login").assertExists().assertIsEnabled()
        onNodeWithText("Login as Guest").assertExists().assertIsEnabled()
        onNode(hasTestTag("ProgressIndicator")).assertDoesNotExist()
    }

    @Test
    fun loadingState_showsProgressAndDisablesButtons(): Unit = composeTestRule.run {
        setContentWithState(LoginViewModel.State(isLoading = true))

        onNode(hasProgressBarRangeInfo(ProgressBarRangeInfo(0.0F, 0.0F..0.0F, 0))).assertExists()
        onNode(hasText("Login") and hasClickAction()).assertIsNotEnabled()
        onNode(hasText("Login as Guest") and hasClickAction()).assertIsNotEnabled()
    }

    @Test
    fun appError_showsErrorDialog(): Unit = composeTestRule.run {
        val error = AppError(
            code = AppErrorCode.LOCAL_ERROR,
            description = "Unexpected error"
        )
        setContentWithState(LoginViewModel.State(appError = error))

        onNodeWithText("Unexpected error").assertExists()
    }

    @Test
    fun loginButtonClick_dispatchesLoginEvent(): Unit = composeTestRule.run {
        var lastEvent: LoginEvent? = null
        setContentWithState(LoginViewModel.State(), onEvent = { lastEvent = it })

        onAllNodes(isEditable())[0].performTextInput("davidluna@mail.com")
        onAllNodes(isEditable())[1].performTextInput("12345678")

        onNodeWithText("Login").performClick()
        assert(lastEvent is LoginEvent.LoginButtonClicked)
    }

    @Test
    fun guestButtonClick_dispatchesGuestEvent(): Unit = composeTestRule.run {
        var lastEvent: LoginEvent? = null
        setContentWithState(LoginViewModel.State(), onEvent = { lastEvent = it })

        onNodeWithText("Login as Guest").performClick()
        assert(lastEvent is LoginEvent.GuestButtonClicked)
    }

    private fun ComposeContentTestRule.setContentWithState(
        state: LoginViewModel.State,
        onEvent: (LoginEvent) -> Unit = {},
    ) {
        setContent {
            TmdbTheme {
                LoginScreen(
                    appError = state.appError,
                    isLoading = state.isLoading,
                    password = state.password,
                    passwordError = state.passwordError,
                    username = state.username,
                    usernameError = state.usernameError,
                    onEvent = onEvent
                )
            }
        }
    }
}