package com.davidluna.architectcoders2024.auth_ui.presenter

import androidx.lifecycle.SavedStateHandle
import app.cash.turbine.test
import com.davidluna.architectcoders2024.core_domain.entities.labels.NavArgument
import com.davidluna.architectcoders2024.fakes.FakeAuthDi
import com.davidluna.architectcoders2024.core_ui.navigation.args.DefaultArgs
import com.davidluna.architectcoders2024.core_ui.navigation.destination.MediaNavigation
import com.davidluna.architectcoders2024.test_shared.fakes.FAKE_QUERY_PARAMS
import com.davidluna.architectcoders2024.test_shared.fakes.fakeEmptySession
import com.davidluna.architectcoders2024.test_shared.rules.CoroutineTestRule
import com.google.common.truth.Truth
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class LoginIntegrationTest {

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    private val initialState = LoginViewModel.LoginState()

    @Test
    fun `GIVEN (user is guest) WHEN (event is GuestButtonCLicked) THEN (should go through guest session authentication process)`() =
        runTest {
            val expected = initialState.copy(
                destination = MediaNavigation.MediaCatalog,
                session = fakeEmptySession
            )
            val savedStateHandle =
                SavedStateHandle(mapOf(NavArgument.APPROVED to DefaultArgs.Auth.defaultValue))
            val viewModel = buildViewModel(savedStateHandle)

            viewModel.sendEvent(LoginEvent.GuestButtonCLicked)

            viewModel.state.test {
                Truth.assertThat(awaitItem()).isEqualTo(initialState)
                awaitItem()
                awaitItem()
                awaitItem()
                Truth.assertThat(awaitItem()).isEqualTo(expected)
                cancelAndIgnoreRemainingEvents()
            }
        }

    @Test
    fun `GIVEN (user is registered) WHEN (event is OnLoginClick) THEN (should go through authentication process)`() =
        runTest {
            val firstExpected = initialState.copy(
                token = "a84c69ad82b2759b5fa9e52ab11f788de81cb464",
                launchTMDBWeb = true,
                session = fakeEmptySession
            )
            val savedStateHandle =
                SavedStateHandle(mapOf(NavArgument.APPROVED to DefaultArgs.Auth.defaultValue))

            val viewModel = buildViewModel(savedStateHandle)
            viewModel.sendEvent(LoginEvent.LoginButtonClicked)

            viewModel.state.test {
                Truth.assertThat(awaitItem()).isEqualTo(initialState)
                awaitItem()
                awaitItem()
                awaitItem()
                Truth.assertThat(awaitItem()).isEqualTo(firstExpected)
                cancelAndIgnoreRemainingEvents()
            }

            val secondExpected =
                initialState.copy(
                    destination = MediaNavigation.MediaCatalog,
                    session = fakeEmptySession
                )
            val secondSavedStateHandle =
                SavedStateHandle(mapOf(NavArgument.APPROVED to FAKE_QUERY_PARAMS))
            val newViewModel = buildViewModel(secondSavedStateHandle)

            newViewModel.state.test {
                Truth.assertThat(awaitItem()).isEqualTo(initialState)
                awaitItem()
                awaitItem()
                awaitItem()
                awaitItem()
                awaitItem()
                Truth.assertThat(awaitItem()).isEqualTo(secondExpected)
                cancelAndIgnoreRemainingEvents()
            }
        }

    private fun buildViewModel(
        savedStateHandle: SavedStateHandle,
    ): LoginViewModel {
        return LoginViewModel(savedStateHandle, FakeAuthDi().loginViewModelUseCases)
    }


}

