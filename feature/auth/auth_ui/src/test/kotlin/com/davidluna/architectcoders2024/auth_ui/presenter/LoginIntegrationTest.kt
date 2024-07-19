package com.davidluna.architectcoders2024.auth_ui.presenter

import androidx.lifecycle.SavedStateHandle
import app.cash.turbine.test
import com.davidluna.architectcoders2024.core_domain.core_entities.labels.NavArgument
import com.davidluna.architectcoders2024.navigation.domain.destination.MediaNavigation
import com.davidluna.architectcoders2024.test_shared.domain.FAKE_QUERY_PARAMS
import com.davidluna.architectcoders2024.test_shared_framework.integration.di.UseCasesModuleDI
import com.davidluna.architectcoders2024.test_shared_framework.rules.CoroutineTestRule
import com.google.common.truth.Truth
import kotlinx.coroutines.flow.onEach
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
    fun `GIVEN (user is guest) WHEN (event is CreateGuestSession) THEN (should go through guest session authentication process)`() =
        runTest {
            val expected = initialState.copy(destination = MediaNavigation.MediaCatalog)
            val viewModel = buildViewModel()

            viewModel.sendEvent(LoginEvent.CreateGuestSession)

            viewModel.state.onEach { println("<-- $it") }.test {
                Truth.assertThat(awaitItem()).isEqualTo(initialState)
                Truth.assertThat(awaitItem()).isEqualTo(initialState.copy(isLoading = true))
                Truth.assertThat(awaitItem()).isEqualTo(
                    initialState.copy(
                        isLoading = true,
                        destination = MediaNavigation.MediaCatalog
                    )
                )
                Truth.assertThat(awaitItem()).isEqualTo(expected)
                cancelAndIgnoreRemainingEvents()
            }
        }

    // first part of test is to check if the token is being generated and the web is being launched
    // once user is authenticated, viewmodel is recreated,
    // args passed through savedStateHandle from deeplink query params for the next part of the test
    @Test
    fun `GIVEN (user is registered) WHEN (event is OnLoginClick) THEN (should go through authentication process)`() =
        runTest {
            val firstExpected = initialState.copy(
                token = "a84c69ad82b2759b5fa9e52ab11f788de81cb464",
                launchTMDBWeb = true
            )
            val savedStateHandle = SavedStateHandle(mapOf(NavArgument.APPROVED to null))

            val viewModel = buildViewModel()
            viewModel.sendEvent(LoginEvent.OnLoginClicked)

            viewModel.state.onEach { println("<-- $it") }.test {
                Truth.assertThat(awaitItem()).isEqualTo(initialState)
                Truth.assertThat(awaitItem()).isEqualTo(initialState.copy(isLoading = true))
                Truth.assertThat(awaitItem()).isEqualTo(firstExpected.copy(isLoading = true))
                Truth.assertThat(awaitItem()).isEqualTo(firstExpected)
                cancelAndIgnoreRemainingEvents()
            }

            val secondExpected =
                initialState.copy(destination = MediaNavigation.MediaCatalog, bioSuccess = true)
            val secondSavedStateHandle =
                SavedStateHandle(mapOf(NavArgument.APPROVED to FAKE_QUERY_PARAMS))
            val newViewModel = buildViewModel(secondSavedStateHandle)

            newViewModel.state.onEach { println("<-- SECOND $it") }.test {
                Truth.assertThat(awaitItem()).isEqualTo(initialState)
                Truth.assertThat(awaitItem().isLoading).isTrue()
                Truth.assertThat(awaitItem().bioSuccess).isTrue()
                Truth.assertThat(awaitItem().isLoading).isFalse()
                Truth.assertThat(awaitItem().isLoading).isTrue()
                Truth.assertThat(awaitItem()).isEqualTo(secondExpected.copy(isLoading = true))
                Truth.assertThat(awaitItem()).isEqualTo(secondExpected)
                cancelAndIgnoreRemainingEvents()
            }
        }

    private fun buildViewModel(
        savedStateHandle: SavedStateHandle =
            SavedStateHandle(mapOf(NavArgument.APPROVED to null))
    ): LoginViewModel {
        return LoginViewModel(savedStateHandle, UseCasesModuleDI().loginViewModelUseCases)
    }
}

