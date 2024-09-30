package com.davidluna.tmdb.auth_ui.presenter

import app.cash.turbine.test
import com.davidluna.tmdb.core_ui.navigation.destination.AuthNavigation.Login
import com.davidluna.tmdb.core_ui.navigation.destination.MediaNavigation
import com.davidluna.tmdb.fakes.FakeAuthDi
import com.davidluna.tmdb.test_shared.fakes.FAKE_REQUEST_TOKEN
import com.davidluna.tmdb.test_shared.fakes.fakeEmptySession
import com.davidluna.tmdb.test_shared.rules.CoroutineTestRule
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
    private val loginArgs: Login = Login(
        requestToken = FAKE_REQUEST_TOKEN,
        approved = true,
        hideAppBar = true
    )
    @Test
    fun `GIVEN (user is guest) WHEN (event is GuestButtonCLicked) THEN (should go through guest session authentication process)`() =
        runTest {
            val expected = initialState.copy(
                destination = MediaNavigation.MediaCatalog(),
                session = fakeEmptySession
            )
            val viewModel = buildViewModel()

            viewModel.onEvent(LoginEvent.GuestButtonCLicked)

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

            val viewModel = buildViewModel()
            viewModel.onEvent(LoginEvent.LoginButtonClicked)

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
                    destination = MediaNavigation.MediaCatalog(),
                    session = fakeEmptySession
                )
            val newViewModel = buildViewModel(loginArgs)

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
        loginArgs: Login = Login(),
    ): LoginViewModel {
        return LoginViewModel(loginArgs, FakeAuthDi().loginViewModelUseCases)
    }

}

