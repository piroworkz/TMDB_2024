package com.davidluna.tmdb.auth_ui.presenter

import app.cash.turbine.test
import arrow.core.left
import arrow.core.right
import com.davidluna.tmdb.auth_domain.usecases.CreateGuestSessionIdUseCase
import com.davidluna.tmdb.auth_domain.usecases.CreateRequestTokenUseCase
import com.davidluna.tmdb.auth_domain.usecases.CreateSessionUseCase
import com.davidluna.tmdb.auth_domain.usecases.GetUserAccountUseCase
import com.davidluna.tmdb.auth_domain.usecases.LoginViewModelUseCases
import com.davidluna.tmdb.core_ui.navigation.destination.AuthNavigation.Login
import com.davidluna.tmdb.core_ui.navigation.destination.MediaNavigation
import com.davidluna.tmdb.test_shared.fakes.FAKE_REQUEST_TOKEN
import com.davidluna.tmdb.test_shared.fakes.fakeGuestSession
import com.davidluna.tmdb.test_shared.fakes.fakeLoginRequest
import com.davidluna.tmdb.test_shared.fakes.fakeSession
import com.davidluna.tmdb.test_shared.fakes.fakeTokenResponse
import com.davidluna.tmdb.test_shared.fakes.fakeUnknownAppError
import com.davidluna.tmdb.test_shared.fakes.fakeUserAccount
import com.davidluna.tmdb.test_shared.rules.CoroutineTestRule
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class LoginViewModelTest {

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    @Mock
    lateinit var createRequestTokenUseCase: CreateRequestTokenUseCase

    @Mock
    lateinit var createSessionUseCase: CreateSessionUseCase

    @Mock
    lateinit var createGuestSessionIdUseCase: CreateGuestSessionIdUseCase

    @Mock
    lateinit var getUserAccountUseCase: GetUserAccountUseCase

    private lateinit var useCases: LoginViewModelUseCases

    private val initialState: LoginViewModel.LoginState = LoginViewModel.LoginState()
    private val args: Login = Login(
        requestToken = FAKE_REQUEST_TOKEN,
        approved = true,
        hideAppBar = true
    )

    @Before
    fun setUp() {
        useCases = LoginViewModelUseCases(
            createRequestTokenUseCase,
            createSessionUseCase,
            createGuestSessionIdUseCase,
            getUserAccountUseCase
        )
    }

    @Test
    fun `GIVEN (Session !exists and event received is GuestButtonClicked) WHEN (createGuestSessionUseCase invoke succeeds) THEN (should update destination state = MediaNavigation MediaCatalog)`() =
        runTest {
            val expected = initialState.copy(destination = MediaNavigation.MediaCatalog())

            whenever(createGuestSessionIdUseCase()).thenReturn(fakeGuestSession.right())

            val viewModel = buildViewModel()
            viewModel.onEvent(LoginEvent.GuestButtonCLicked)

            viewModel.state.onEach { println("<-- state $it") }.test {
                assertThat(awaitItem()).isEqualTo(initialState)
                assertThat(awaitItem()).isEqualTo(expected)
                cancelAndConsumeRemainingEvents()
            }
            verify(createGuestSessionIdUseCase).invoke()
        }

    @Test
    fun `GIVEN (Session !exists and event received is GuestButtonClicked) WHEN (createGuestSessionUseCase invoke fails) THEN (should update appError state = AppError Message)`() =
        runTest {
            val expected =
                initialState.copy(appError = fakeUnknownAppError)
            whenever(createGuestSessionIdUseCase()).thenReturn(fakeUnknownAppError.left())

            val viewModel = buildViewModel()
            viewModel.onEvent(LoginEvent.GuestButtonCLicked)

            viewModel.state.test {
                assertThat(awaitItem()).isEqualTo(initialState)
                assertThat(awaitItem()).isEqualTo(expected)
                cancelAndConsumeRemainingEvents()
            }
            verify(createGuestSessionIdUseCase).invoke()
        }

    @Test
    fun `GIVEN (Session !exists and event received is LoginButtonClicked) WHEN (createRequestTokenUseCase invoke succeeds) THEN (should update launchTMDBWeb state = true)`() =
        runTest {
            val expected =
                initialState.copy(
                    launchTMDBWeb = true,
                    token = fakeTokenResponse.requestToken
                )
            whenever(createRequestTokenUseCase()).thenReturn(fakeTokenResponse.right())

            val viewModel = buildViewModel()
            viewModel.onEvent(LoginEvent.LoginButtonClicked)

            viewModel.state.test {
                assertThat(awaitItem()).isEqualTo(initialState)
                assertThat(awaitItem()).isEqualTo(expected)
                cancelAndConsumeRemainingEvents()
            }
            verify(createRequestTokenUseCase).invoke()
        }

    @Test
    fun `GIVEN (Session !exists and event received is LoginButtonClicked) WHEN (createRequestTokenUseCase invoke fails) THEN (should update appError state = AppError Message)`() =
        runTest {
            val expected =
                initialState.copy(appError = fakeUnknownAppError)
            whenever(createRequestTokenUseCase()).thenReturn(fakeUnknownAppError.left())

            val viewModel = buildViewModel()
            viewModel.onEvent(LoginEvent.LoginButtonClicked)

            viewModel.state.test {
                assertThat(awaitItem()).isEqualTo(initialState)
                assertThat(awaitItem()).isEqualTo(expected)
                cancelAndConsumeRemainingEvents()
            }
            verify(createRequestTokenUseCase).invoke()
        }

    @Test
    fun `GIVEN (launched and approved login on TMDB site) WHEN (deeplink args != defaultValue) THEN (should fetch user session and update destination state = MediaNavigation MediaCatalog)`() =
        runTest {
            val expected = initialState.copy(destination = MediaNavigation.MediaCatalog())
            whenever(createSessionUseCase(fakeLoginRequest)).thenReturn(fakeSession.right())
            whenever(getUserAccountUseCase()).thenReturn(fakeUserAccount.right())

            val viewModel = buildViewModel(args)

            viewModel.state.test {
                assertThat(awaitItem()).isEqualTo(initialState)
                assertThat(awaitItem()).isEqualTo(expected)
                cancelAndConsumeRemainingEvents()
            }
            verify(createSessionUseCase).invoke(fakeLoginRequest)
            verify(getUserAccountUseCase).invoke()
        }

    @Test
    fun `GIVEN (launched and approved login on TMDB site) WHEN (deeplink args != defaultValue and createSessionUseCase fails) THEN (should update appError state = AppError Message)`() =
        runTest {
            val expected =
                initialState.copy(appError = fakeUnknownAppError)

            whenever(createSessionUseCase(fakeLoginRequest)).thenReturn(
                fakeUnknownAppError.left()
            )

            val viewModel = buildViewModel(args)

            viewModel.state.test {
                assertThat(awaitItem()).isEqualTo(initialState)
                assertThat(awaitItem()).isEqualTo(expected)
                cancelAndConsumeRemainingEvents()
            }
            verify(createSessionUseCase).invoke(fakeLoginRequest)
        }


    @Test
    fun `GIVEN (launched and approved login on TMDB site) WHEN (deeplink args != defaultValue and getUserAccountUseCase fails) THEN (should update appError state = AppError Message)`() =
        runTest {
            val expected =
                initialState.copy(appError = fakeUnknownAppError)

            whenever(createSessionUseCase(fakeLoginRequest)).thenReturn(
                fakeSession.right()
            )
            whenever(getUserAccountUseCase()).thenReturn(fakeUnknownAppError.left())

            val viewModel = buildViewModel(args)

            viewModel.state.test {
                assertThat(awaitItem()).isEqualTo(initialState)
                assertThat(awaitItem()).isEqualTo(expected)
                cancelAndConsumeRemainingEvents()
            }
            verify(createSessionUseCase).invoke(fakeLoginRequest)
            verify(getUserAccountUseCase).invoke()
        }

    private fun buildViewModel(loginArgs: Login = Login()): LoginViewModel =
        LoginViewModel(loginArgs, useCases)
}