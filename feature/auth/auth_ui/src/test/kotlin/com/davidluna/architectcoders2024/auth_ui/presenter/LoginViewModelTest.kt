package com.davidluna.architectcoders2024.auth_ui.presenter

import androidx.lifecycle.SavedStateHandle
import app.cash.turbine.test
import arrow.core.left
import arrow.core.right
import com.davidluna.architectcoders2024.auth_domain.usecases.CreateGuestSessionIdUseCase
import com.davidluna.architectcoders2024.auth_domain.usecases.CreateRequestTokenUseCase
import com.davidluna.architectcoders2024.auth_domain.usecases.CreateSessionUseCase
import com.davidluna.architectcoders2024.auth_domain.usecases.ExtractQueryArgumentsUseCase
import com.davidluna.architectcoders2024.auth_domain.usecases.GetUserAccountUseCase
import com.davidluna.architectcoders2024.auth_domain.usecases.GuestSessionNotExpiredUseCase
import com.davidluna.architectcoders2024.auth_domain.usecases.LoginViewModelUseCases
import com.davidluna.architectcoders2024.core_domain.entities.labels.NavArgument
import com.davidluna.architectcoders2024.core_domain.usecases.datastore.SessionUseCase
import com.davidluna.architectcoders2024.navigation.domain.destination.MediaNavigation
import com.davidluna.architectcoders2024.auth_domain.fakes.FAKE_QUERY_PARAMS
import com.davidluna.architectcoders2024.auth_domain.fakes.fakeEmptySession
import com.davidluna.architectcoders2024.auth_domain.fakes.fakeGuestSession
import com.davidluna.architectcoders2024.auth_domain.fakes.fakeLoginRequest
import com.davidluna.architectcoders2024.auth_domain.fakes.fakeQueryArgs
import com.davidluna.architectcoders2024.auth_domain.fakes.fakeSession
import com.davidluna.architectcoders2024.auth_domain.fakes.fakeTokenResponse
import com.davidluna.architectcoders2024.test_shared.fakes.fakeUnknownAppError
import com.davidluna.architectcoders2024.auth_domain.fakes.fakeUserAccount
import com.davidluna.architectcoders2024.test_shared.rules.CoroutineTestRule
import com.google.common.truth.Truth
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.any
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

    @Mock
    lateinit var sessionUseCase: SessionUseCase

    @Mock
    lateinit var extractQueryArgumentsUseCase: ExtractQueryArgumentsUseCase

    @Mock
    lateinit var guestSessionNotExpiredUseCase: GuestSessionNotExpiredUseCase

    private lateinit var useCases: LoginViewModelUseCases

    private val initialState: LoginViewModel.LoginState = LoginViewModel.LoginState()

    @Before
    fun setUp() {
        useCases = LoginViewModelUseCases(
            createRequestTokenUseCase,
            createSessionUseCase,
            createGuestSessionIdUseCase,
            getUserAccountUseCase,
            sessionUseCase,
            extractQueryArgumentsUseCase,
            guestSessionNotExpiredUseCase
        )
    }

    @Test
    fun `GIVEN (Session !exists and event received is GuestButtonClicked) WHEN (createGuestSessionUseCase invoke succeeds) THEN (should update destination state = MediaNavigation MediaCatalog)`() =
        runTest {
            val savedStateHandle = SavedStateHandle()
            val expected = initialState.copy(
                destination = MediaNavigation.MediaCatalog,
                session = com.davidluna.architectcoders2024.auth_domain.fakes.fakeEmptySession
            )
            whenever(createGuestSessionIdUseCase()).thenReturn(com.davidluna.architectcoders2024.auth_domain.fakes.fakeGuestSession.right())
            whenever(sessionUseCase()).thenReturn(flowOf(com.davidluna.architectcoders2024.auth_domain.fakes.fakeEmptySession))

            val viewModel = buildViewModel(savedStateHandle)
            viewModel.sendEvent(LoginEvent.GuestButtonCLicked)

            viewModel.state.test {
                Truth.assertThat(awaitItem()).isEqualTo(initialState)
                awaitItem()
                awaitItem()
                awaitItem()
                Truth.assertThat(awaitItem()).isEqualTo(expected)
                cancelAndConsumeRemainingEvents()
            }
            verify(createGuestSessionIdUseCase).invoke()
            verify(sessionUseCase).invoke()
        }

    @Test
    fun `GIVEN (Session !exists and event received is GuestButtonClicked) WHEN (createGuestSessionUseCase invoke fails) THEN (should update appError state = AppError Message)`() =
        runTest {
            val savedStateHandle = SavedStateHandle()
            val expected =
                initialState.copy(appError = fakeUnknownAppError, session = com.davidluna.architectcoders2024.auth_domain.fakes.fakeEmptySession)
            whenever(createGuestSessionIdUseCase()).thenReturn(fakeUnknownAppError.left())
            whenever(sessionUseCase()).thenReturn(flowOf(com.davidluna.architectcoders2024.auth_domain.fakes.fakeEmptySession))

            val viewModel = buildViewModel(savedStateHandle)
            viewModel.sendEvent(LoginEvent.GuestButtonCLicked)

            viewModel.state.test {
                Truth.assertThat(awaitItem()).isEqualTo(initialState)
                awaitItem()
                awaitItem()
                awaitItem()
                Truth.assertThat(awaitItem()).isEqualTo(expected)
                cancelAndConsumeRemainingEvents()
            }
            verify(createGuestSessionIdUseCase).invoke()
            verify(sessionUseCase).invoke()
        }

    @Test
    fun `GIVEN (Session exists and event received is GuestButtonClicked) WHEN (sessionUseCase invoke succeeds) THEN (should update destination state = MediaNavigation MediaCatalog)`() =
        runTest {
            val savedStateHandle = SavedStateHandle()
            val expected = initialState.copy(
                destination = MediaNavigation.MediaCatalog,
                session = com.davidluna.architectcoders2024.auth_domain.fakes.fakeGuestSession
            )
            whenever(sessionUseCase()).thenReturn(flowOf(com.davidluna.architectcoders2024.auth_domain.fakes.fakeGuestSession))
            whenever(guestSessionNotExpiredUseCase(any())).thenReturn(true)

            val viewModel = buildViewModel(savedStateHandle)
            viewModel.sendEvent(LoginEvent.GuestButtonCLicked)

            viewModel.state.test {
                Truth.assertThat(awaitItem()).isEqualTo(initialState)
                awaitItem()
                awaitItem()
                awaitItem()
                Truth.assertThat(awaitItem()).isEqualTo(expected)
                cancelAndConsumeRemainingEvents()
            }
            verify(sessionUseCase).invoke()
            verify(guestSessionNotExpiredUseCase).invoke(any())
        }


    @Test
    fun `GIVEN (Session !exists and event received is LoginButtonClicked) WHEN (createRequestTokenUseCase invoke succeeds) THEN (should update launchTMDBWeb state = true)`() =
        runTest {
            val savedStateHandle = SavedStateHandle()
            val expected =
                initialState.copy(
                    launchTMDBWeb = true,
                    token = com.davidluna.architectcoders2024.auth_domain.fakes.fakeTokenResponse.requestToken,
                    session = com.davidluna.architectcoders2024.auth_domain.fakes.fakeEmptySession
                )
            whenever(createRequestTokenUseCase()).thenReturn(com.davidluna.architectcoders2024.auth_domain.fakes.fakeTokenResponse.right())
            whenever(sessionUseCase()).thenReturn(flowOf(com.davidluna.architectcoders2024.auth_domain.fakes.fakeEmptySession))

            val viewModel = buildViewModel(savedStateHandle)
            viewModel.sendEvent(LoginEvent.LoginButtonClicked)

            viewModel.state.test {
                Truth.assertThat(awaitItem()).isEqualTo(initialState)
                awaitItem()
                awaitItem()
                awaitItem()
                Truth.assertThat(awaitItem()).isEqualTo(expected)
                cancelAndConsumeRemainingEvents()
            }
            verify(createRequestTokenUseCase).invoke()
            verify(sessionUseCase).invoke()
        }

    @Test
    fun `GIVEN (Session !exists and event received is LoginButtonClicked) WHEN (createRequestTokenUseCase invoke fails) THEN (should update appError state = AppError Message)`() =
        runTest {
            val savedStateHandle = SavedStateHandle()
            val expected =
                initialState.copy(appError = fakeUnknownAppError, session = com.davidluna.architectcoders2024.auth_domain.fakes.fakeEmptySession)
            whenever(createRequestTokenUseCase()).thenReturn(fakeUnknownAppError.left())
            whenever(sessionUseCase()).thenReturn(flowOf(com.davidluna.architectcoders2024.auth_domain.fakes.fakeEmptySession))

            val viewModel = buildViewModel(savedStateHandle)
            viewModel.sendEvent(LoginEvent.LoginButtonClicked)

            viewModel.state.test {
                Truth.assertThat(awaitItem()).isEqualTo(initialState)
                awaitItem()
                awaitItem()
                awaitItem()
                Truth.assertThat(awaitItem()).isEqualTo(expected)
                cancelAndConsumeRemainingEvents()
            }
            verify(createRequestTokenUseCase).invoke()
            verify(sessionUseCase).invoke()
        }

    @Test
    fun `GIVEN (launched and approved login on TMDB site) WHEN (deeplink args != defaultValue) THEN (should fetch user session and update destination state = MediaNavigation MediaCatalog)`() =
        runTest {
            val savedStateHandle =
                SavedStateHandle(mapOf(NavArgument.APPROVED to com.davidluna.architectcoders2024.auth_domain.fakes.FAKE_QUERY_PARAMS))
            val expected = initialState.copy(
                destination = MediaNavigation.MediaCatalog,
                session = com.davidluna.architectcoders2024.auth_domain.fakes.fakeEmptySession,
            )
            whenever(extractQueryArgumentsUseCase(any())).thenReturn(com.davidluna.architectcoders2024.auth_domain.fakes.fakeQueryArgs)
            whenever(sessionUseCase()).thenReturn(flowOf(com.davidluna.architectcoders2024.auth_domain.fakes.fakeEmptySession))
            whenever(createSessionUseCase(com.davidluna.architectcoders2024.auth_domain.fakes.fakeLoginRequest)).thenReturn(
                com.davidluna.architectcoders2024.auth_domain.fakes.fakeSession.right())
            whenever(getUserAccountUseCase()).thenReturn(com.davidluna.architectcoders2024.auth_domain.fakes.fakeUserAccount.right())

            val viewModel = buildViewModel(savedStateHandle)

            viewModel.state.test {
                Truth.assertThat(awaitItem()).isEqualTo(initialState)
                awaitItem()
                awaitItem()
                awaitItem()
                awaitItem()
                awaitItem()
                Truth.assertThat(awaitItem()).isEqualTo(expected)
                cancelAndConsumeRemainingEvents()
            }
            verify(extractQueryArgumentsUseCase).invoke(com.davidluna.architectcoders2024.auth_domain.fakes.FAKE_QUERY_PARAMS)
            verify(sessionUseCase).invoke()
            verify(createSessionUseCase).invoke(com.davidluna.architectcoders2024.auth_domain.fakes.fakeLoginRequest)
            verify(getUserAccountUseCase).invoke()
        }

    @Test
    fun `GIVEN (launched and approved login on TMDB site) WHEN (deeplink args != defaultValue and createSessionUseCase fails) THEN (should update appError state = AppError Message)`() =
        runTest {
            val savedStateHandle =
                SavedStateHandle(mapOf(NavArgument.APPROVED to com.davidluna.architectcoders2024.auth_domain.fakes.FAKE_QUERY_PARAMS))
            val expected =
                initialState.copy(appError = fakeUnknownAppError, session = com.davidluna.architectcoders2024.auth_domain.fakes.fakeEmptySession)

            whenever(extractQueryArgumentsUseCase(any())).thenReturn(com.davidluna.architectcoders2024.auth_domain.fakes.fakeQueryArgs)
            whenever(sessionUseCase()).thenReturn(flowOf(com.davidluna.architectcoders2024.auth_domain.fakes.fakeEmptySession))
            whenever(createSessionUseCase(com.davidluna.architectcoders2024.auth_domain.fakes.fakeLoginRequest)).thenReturn(
                fakeUnknownAppError.left())

            val viewModel = buildViewModel(savedStateHandle)

            viewModel.state.test {
                Truth.assertThat(awaitItem()).isEqualTo(initialState)
                awaitItem()
                awaitItem()
                awaitItem()
                Truth.assertThat(awaitItem()).isEqualTo(expected)
                cancelAndConsumeRemainingEvents()
            }
            verify(extractQueryArgumentsUseCase).invoke(com.davidluna.architectcoders2024.auth_domain.fakes.FAKE_QUERY_PARAMS)
            verify(sessionUseCase).invoke()
            verify(createSessionUseCase).invoke(com.davidluna.architectcoders2024.auth_domain.fakes.fakeLoginRequest)
        }


    @Test
    fun `GIVEN (launched and approved login on TMDB site) WHEN (deeplink args != defaultValue and getUserAccountUseCase fails) THEN (should update appError state = AppError Message)`() =
        runTest {
            val savedStateHandle =
                SavedStateHandle(mapOf(NavArgument.APPROVED to com.davidluna.architectcoders2024.auth_domain.fakes.FAKE_QUERY_PARAMS))
            val expected =
                initialState.copy(appError = fakeUnknownAppError, session = com.davidluna.architectcoders2024.auth_domain.fakes.fakeEmptySession)

            whenever(extractQueryArgumentsUseCase(any())).thenReturn(com.davidluna.architectcoders2024.auth_domain.fakes.fakeQueryArgs)
            whenever(sessionUseCase()).thenReturn(flowOf(com.davidluna.architectcoders2024.auth_domain.fakes.fakeEmptySession))
            whenever(createSessionUseCase(com.davidluna.architectcoders2024.auth_domain.fakes.fakeLoginRequest)).thenReturn(
                com.davidluna.architectcoders2024.auth_domain.fakes.fakeSession.right())
            whenever(getUserAccountUseCase()).thenReturn(fakeUnknownAppError.left())

            val viewModel = buildViewModel(savedStateHandle)

            viewModel.state.test {
                Truth.assertThat(awaitItem()).isEqualTo(initialState)
                awaitItem()
                awaitItem()
                awaitItem()
                awaitItem()
                awaitItem()
                Truth.assertThat(awaitItem()).isEqualTo(expected)
                cancelAndConsumeRemainingEvents()
            }
            verify(extractQueryArgumentsUseCase).invoke(com.davidluna.architectcoders2024.auth_domain.fakes.FAKE_QUERY_PARAMS)
            verify(sessionUseCase).invoke()
            verify(createSessionUseCase).invoke(com.davidluna.architectcoders2024.auth_domain.fakes.fakeLoginRequest)
            verify(getUserAccountUseCase).invoke()
        }

    @Test
    fun `GIVEN (Session exists) WHEN (sessionUseCase collect succeeds) THEN (should update launchBioPrompt state = true)`() =
        runTest {
            val savedStateHandle = SavedStateHandle()
            val expected = initialState.copy(session = com.davidluna.architectcoders2024.auth_domain.fakes.fakeSession, launchBioPrompt = true)
            whenever(sessionUseCase()).thenReturn(flowOf(com.davidluna.architectcoders2024.auth_domain.fakes.fakeSession))

            val viewModel = buildViewModel(savedStateHandle)

            viewModel.state.test {
                Truth.assertThat(awaitItem()).isEqualTo(initialState)
                Truth.assertThat(awaitItem()).isEqualTo(expected)
                cancelAndConsumeRemainingEvents()
            }
            verify(sessionUseCase).invoke()
        }

    private fun buildViewModel(savedStateHandle: SavedStateHandle): LoginViewModel =
        LoginViewModel(savedStateHandle, useCases)
}