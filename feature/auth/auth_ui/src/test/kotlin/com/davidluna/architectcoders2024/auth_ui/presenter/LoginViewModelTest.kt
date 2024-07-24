package com.davidluna.architectcoders2024.auth_ui.presenter

import androidx.lifecycle.SavedStateHandle
import app.cash.turbine.test
import arrow.core.left
import arrow.core.right
import com.davidluna.architectcoders2024.auth_domain.auth_domain_usecases.session.CreateGuestSessionIdUseCase
import com.davidluna.architectcoders2024.auth_domain.auth_domain_usecases.session.CreateRequestTokenUseCase
import com.davidluna.architectcoders2024.auth_domain.auth_domain_usecases.session.CreateSessionUseCase
import com.davidluna.architectcoders2024.auth_domain.auth_domain_usecases.session.ExtractQueryArgumentsUseCase
import com.davidluna.architectcoders2024.auth_domain.auth_domain_usecases.session.GetUserAccountUseCase
import com.davidluna.architectcoders2024.auth_domain.auth_domain_usecases.session.GuestSessionNotExpiredUseCase
import com.davidluna.architectcoders2024.auth_domain.auth_domain_usecases.session.LoginViewModelUseCases
import com.davidluna.architectcoders2024.core_domain.core_entities.labels.NavArgument
import com.davidluna.architectcoders2024.core_domain.core_usecases.datastore.SessionUseCase
import com.davidluna.architectcoders2024.navigation.domain.destination.MediaNavigation
import com.davidluna.architectcoders2024.test_shared.domain.FAKE_QUERY_PARAMS
import com.davidluna.architectcoders2024.test_shared.domain.fakeEmptySession
import com.davidluna.architectcoders2024.test_shared.domain.fakeGuestSession
import com.davidluna.architectcoders2024.test_shared.domain.fakeLoginRequest
import com.davidluna.architectcoders2024.test_shared.domain.fakeQueryArgs
import com.davidluna.architectcoders2024.test_shared.domain.fakeSession
import com.davidluna.architectcoders2024.test_shared.domain.fakeTokenResponse
import com.davidluna.architectcoders2024.test_shared.domain.fakeUnknownAppError
import com.davidluna.architectcoders2024.test_shared.domain.fakeUserAccount
import com.davidluna.architectcoders2024.test_shared_framework.rules.CoroutineTestRule
import com.google.common.truth.Truth
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.onEach
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
                session = fakeEmptySession
            )
            whenever(createGuestSessionIdUseCase()).thenReturn(fakeGuestSession.right())
            whenever(sessionUseCase()).thenReturn(flowOf(fakeEmptySession))

            val viewModel = buildViewModel(savedStateHandle)
            viewModel.sendEvent(LoginEvent.GuestButtonCLicked)

            viewModel.state.onEach { println("<-- $it") }.test {
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
                initialState.copy(appError = fakeUnknownAppError, session = fakeEmptySession)
            whenever(createGuestSessionIdUseCase()).thenReturn(fakeUnknownAppError.left())
            whenever(sessionUseCase()).thenReturn(flowOf(fakeEmptySession))

            val viewModel = buildViewModel(savedStateHandle)
            viewModel.sendEvent(LoginEvent.GuestButtonCLicked)

            viewModel.state.onEach { println("<-- $it") }.test {
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
                session = fakeGuestSession
            )
            whenever(sessionUseCase()).thenReturn(flowOf(fakeGuestSession))
            whenever(guestSessionNotExpiredUseCase(any())).thenReturn(true)

            val viewModel = buildViewModel(savedStateHandle)
            viewModel.sendEvent(LoginEvent.GuestButtonCLicked)

            viewModel.state.onEach { println("<-- $it") }.test {
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
                    token = fakeTokenResponse.requestToken,
                    session = fakeEmptySession
                )
            whenever(createRequestTokenUseCase()).thenReturn(fakeTokenResponse.right())
            whenever(sessionUseCase()).thenReturn(flowOf(fakeEmptySession))

            val viewModel = buildViewModel(savedStateHandle)
            viewModel.sendEvent(LoginEvent.LoginButtonClicked)

            viewModel.state.onEach { println("<-- $it") }.test {
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
                initialState.copy(appError = fakeUnknownAppError, session = fakeEmptySession)
            whenever(createRequestTokenUseCase()).thenReturn(fakeUnknownAppError.left())
            whenever(sessionUseCase()).thenReturn(flowOf(fakeEmptySession))

            val viewModel = buildViewModel(savedStateHandle)
            viewModel.sendEvent(LoginEvent.LoginButtonClicked)

            viewModel.state.onEach { println("<-- $it") }.test {
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
                SavedStateHandle(mapOf(NavArgument.APPROVED to FAKE_QUERY_PARAMS))
            val expected = initialState.copy(
                destination = MediaNavigation.MediaCatalog,
                session = fakeEmptySession,
            )
            whenever(extractQueryArgumentsUseCase(any())).thenReturn(fakeQueryArgs)
            whenever(sessionUseCase()).thenReturn(flowOf(fakeEmptySession))
            whenever(createSessionUseCase(fakeLoginRequest)).thenReturn(fakeSession.right())
            whenever(getUserAccountUseCase()).thenReturn(fakeUserAccount.right())

            val viewModel = buildViewModel(savedStateHandle)

            viewModel.state.onEach { println("<-- $it") }.test {
                Truth.assertThat(awaitItem()).isEqualTo(initialState)
                awaitItem()
                awaitItem()
                awaitItem()
                awaitItem()
                awaitItem()
                Truth.assertThat(awaitItem()).isEqualTo(expected)
                cancelAndConsumeRemainingEvents()
            }
            verify(extractQueryArgumentsUseCase).invoke(FAKE_QUERY_PARAMS)
            verify(sessionUseCase).invoke()
            verify(createSessionUseCase).invoke(fakeLoginRequest)
            verify(getUserAccountUseCase).invoke()
        }

    @Test
    fun `GIVEN (launched and approved login on TMDB site) WHEN (deeplink args != defaultValue and createSessionUseCase fails) THEN (should update appError state = AppError Message)`() =
        runTest {
            val savedStateHandle =
                SavedStateHandle(mapOf(NavArgument.APPROVED to FAKE_QUERY_PARAMS))
            val expected =
                initialState.copy(appError = fakeUnknownAppError, session = fakeEmptySession)

            whenever(extractQueryArgumentsUseCase(any())).thenReturn(fakeQueryArgs)
            whenever(sessionUseCase()).thenReturn(flowOf(fakeEmptySession))
            whenever(createSessionUseCase(fakeLoginRequest)).thenReturn(fakeUnknownAppError.left())

            val viewModel = buildViewModel(savedStateHandle)

            viewModel.state.onEach { println("<-- $it") }.test {
                Truth.assertThat(awaitItem()).isEqualTo(initialState)
                awaitItem()
                awaitItem()
                awaitItem()
                Truth.assertThat(awaitItem()).isEqualTo(expected)
                cancelAndConsumeRemainingEvents()
            }
            verify(extractQueryArgumentsUseCase).invoke(FAKE_QUERY_PARAMS)
            verify(sessionUseCase).invoke()
            verify(createSessionUseCase).invoke(fakeLoginRequest)
        }


    @Test
    fun `GIVEN (launched and approved login on TMDB site) WHEN (deeplink args != defaultValue and getUserAccountUseCase fails) THEN (should update appError state = AppError Message)`() =
        runTest {
            val savedStateHandle =
                SavedStateHandle(mapOf(NavArgument.APPROVED to FAKE_QUERY_PARAMS))
            val expected =
                initialState.copy(appError = fakeUnknownAppError, session = fakeEmptySession)

            whenever(extractQueryArgumentsUseCase(any())).thenReturn(fakeQueryArgs)
            whenever(sessionUseCase()).thenReturn(flowOf(fakeEmptySession))
            whenever(createSessionUseCase(fakeLoginRequest)).thenReturn(fakeSession.right())
            whenever(getUserAccountUseCase()).thenReturn(fakeUnknownAppError.left())

            val viewModel = buildViewModel(savedStateHandle)

            viewModel.state.onEach { println("<-- $it") }.test {
                Truth.assertThat(awaitItem()).isEqualTo(initialState)
                awaitItem()
                awaitItem()
                awaitItem()
                awaitItem()
                awaitItem()
                Truth.assertThat(awaitItem()).isEqualTo(expected)
                cancelAndConsumeRemainingEvents()
            }
            verify(extractQueryArgumentsUseCase).invoke(FAKE_QUERY_PARAMS)
            verify(sessionUseCase).invoke()
            verify(createSessionUseCase).invoke(fakeLoginRequest)
            verify(getUserAccountUseCase).invoke()
        }

    @Test
    fun `GIVEN (Session exists) WHEN (sessionUseCase collect succeeds) THEN (should update launchBioPrompt state = true)`() =
        runTest {
            val savedStateHandle = SavedStateHandle()
            val expected = initialState.copy(session = fakeSession, launchBioPrompt = true)
            whenever(sessionUseCase()).thenReturn(flowOf(fakeSession))

            val viewModel = buildViewModel(savedStateHandle)

            viewModel.state.onEach { println("<-- $it") }.test {
                Truth.assertThat(awaitItem()).isEqualTo(initialState)
                Truth.assertThat(awaitItem()).isEqualTo(expected)
                cancelAndConsumeRemainingEvents()
            }
            verify(sessionUseCase).invoke()
        }

    private fun buildViewModel(savedStateHandle: SavedStateHandle): LoginViewModel =
        LoginViewModel(savedStateHandle, useCases)
}