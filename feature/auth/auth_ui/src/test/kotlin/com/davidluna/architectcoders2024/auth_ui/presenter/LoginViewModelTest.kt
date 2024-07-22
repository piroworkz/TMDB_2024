package com.davidluna.architectcoders2024.auth_ui.presenter

import androidx.lifecycle.SavedStateHandle
import app.cash.turbine.test
import arrow.core.left
import arrow.core.right
import com.davidluna.architectcoders2024.auth_domain.auth_domain_usecases.session.CreateGuestSessionIdUseCase
import com.davidluna.architectcoders2024.auth_domain.auth_domain_usecases.session.CreateRequestTokenUseCase
import com.davidluna.architectcoders2024.auth_domain.auth_domain_usecases.session.CreateSessionIdUseCase
import com.davidluna.architectcoders2024.auth_domain.auth_domain_usecases.session.ExtractQueryArgumentsUseCase
import com.davidluna.architectcoders2024.auth_domain.auth_domain_usecases.session.GetUserAccountUseCase
import com.davidluna.architectcoders2024.auth_domain.auth_domain_usecases.session.LoginViewModelUseCases
import com.davidluna.architectcoders2024.core_domain.core_entities.errors.AppError
import com.davidluna.architectcoders2024.core_domain.core_entities.errors.AppErrorCode
import com.davidluna.architectcoders2024.core_domain.core_entities.labels.NavArgument
import com.davidluna.architectcoders2024.core_domain.core_usecases.datastore.SessionIdUseCase
import com.davidluna.architectcoders2024.navigation.domain.destination.MediaNavigation
import com.davidluna.architectcoders2024.test_shared.domain.FAKE_QUERY_PARAMS
import com.davidluna.architectcoders2024.test_shared.domain.FAKE_SESSION_ID
import com.davidluna.architectcoders2024.test_shared.domain.fakeGuestSession
import com.davidluna.architectcoders2024.test_shared.domain.fakeLoginRequest
import com.davidluna.architectcoders2024.test_shared.domain.fakeQueryArgs
import com.davidluna.architectcoders2024.test_shared.domain.fakeSessionId
import com.davidluna.architectcoders2024.test_shared.domain.fakeTokenResponse
import com.davidluna.architectcoders2024.test_shared.domain.fakeUnknownAppError
import com.davidluna.architectcoders2024.test_shared.domain.fakeUserAccount
import com.davidluna.architectcoders2024.test_shared_framework.rules.CoroutineTestRule
import com.google.common.truth.Truth
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flow
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
    lateinit var createSessionIdUseCase: CreateSessionIdUseCase

    @Mock
    lateinit var createGuestSessionIdUseCase: CreateGuestSessionIdUseCase

    @Mock
    lateinit var getUserAccountUseCase: GetUserAccountUseCase

    @Mock
    lateinit var sessionIdUseCase: SessionIdUseCase

    @Mock
    lateinit var extractQueryArgumentsUseCase: ExtractQueryArgumentsUseCase

    private lateinit var useCases: LoginViewModelUseCases

    private val initialState: LoginViewModel.LoginState = LoginViewModel.LoginState()

    @Before
    fun setUp() {

        useCases = LoginViewModelUseCases(
            createRequestTokenUseCase,
            createSessionIdUseCase,
            createGuestSessionIdUseCase,
            getUserAccountUseCase,
            sessionIdUseCase,
            extractQueryArgumentsUseCase
        )
    }

    @Test
    fun `GIVEN (not loggedIn and event is CreateGuestSession) WHEN (useCase createGuestSessionId succeeds sends event IsLoggedIn) THEN (should update destination state with MediaCatalog)`() =
        runTest {
            val savedStateHandle = SavedStateHandle()

            whenever(useCases.sessionId()).thenReturn(emptyFlow())
            whenever(useCases.createGuestSessionId()).thenReturn(fakeGuestSession.right())

            val viewModel = buildViewModel(savedStateHandle)
            viewModel.sendEvent(LoginEvent.CreateGuestSession)

            viewModel.state.onEach { println("<-- $it") }.test {
                Truth.assertThat(awaitItem()).isEqualTo(initialState)
                Truth.assertThat(awaitItem().isLoading).isTrue()
                Truth.assertThat(awaitItem().destination).isEqualTo(MediaNavigation.MediaCatalog)
                cancelAndIgnoreRemainingEvents()
            }

            verify(useCases.createGuestSessionId).invoke()
            verify(useCases.sessionId).invoke()
        }


    @Test
    fun `GIVEN (not loggedIn and event is CreateGuestSession) WHEN (useCase createGuestSessionId fails) THEN (should update appError state with AppError)`() =
        runTest {
            val savedStateHandle = SavedStateHandle()

            whenever(useCases.sessionId.invoke()).thenReturn(emptyFlow())
            whenever(useCases.createGuestSessionId()).thenReturn(fakeUnknownAppError.left())

            val viewModel = buildViewModel(savedStateHandle)
            viewModel.sendEvent(LoginEvent.CreateGuestSession)

            viewModel.state.onEach { println("<-- $it") }.test {
                Truth.assertThat(awaitItem()).isEqualTo(initialState)
                Truth.assertThat(awaitItem().isLoading).isTrue()
                Truth.assertThat(awaitItem().appError).isEqualTo(fakeUnknownAppError)
                cancelAndIgnoreRemainingEvents()
            }

            verify(useCases.createGuestSessionId).invoke()
            verify(useCases.sessionId).invoke()
        }

    @Test
    fun `GIVEN (not loggedIn and event is OnLoginClicked) WHEN (useCase createRequestToken succeeds) THEN (should update token state with received RequestToken)`() =
        runTest {
            val savedStateHandle = SavedStateHandle()

            val expected = initialState.copy(
                token = fakeTokenResponse.requestToken,
                launchTMDBWeb = true,
                isLoading = true
            )

            whenever(useCases.sessionId()).thenReturn(emptyFlow())
            whenever(useCases.createRequestToken()).thenReturn(fakeTokenResponse.right())

            val viewModel = buildViewModel(savedStateHandle)
            viewModel.sendEvent(LoginEvent.OnLoginClicked)

            viewModel.state.onEach { println("<-- $it") }.test {
                Truth.assertThat(awaitItem()).isEqualTo(initialState)
                Truth.assertThat(awaitItem().isLoading).isTrue()
                Truth.assertThat(awaitItem()).isEqualTo(expected)
                cancelAndIgnoreRemainingEvents()
            }

            verify(useCases.createRequestToken).invoke()
            verify(useCases.sessionId).invoke()
        }


    @Test
    fun `GIVEN (not loggedIn and event is OnLoginClicked) WHEN (useCase createRequestToken fails) THEN (should update appError state = AppError)`() =
        runTest {
            val savedStateHandle = SavedStateHandle()
            val expected = initialState.copy(
                token = fakeTokenResponse.requestToken,
                launchTMDBWeb = true,
                isLoading = true
            )

            whenever(useCases.sessionId()).thenReturn(emptyFlow())
            whenever(useCases.createRequestToken()).thenReturn(fakeTokenResponse.right())

            val viewModel = buildViewModel(savedStateHandle)
            viewModel.sendEvent(LoginEvent.OnLoginClicked)

            viewModel.state.onEach { println("<-- $it") }.test {
                Truth.assertThat(awaitItem()).isEqualTo(initialState)
                Truth.assertThat(awaitItem().isLoading).isTrue()
                Truth.assertThat(awaitItem()).isEqualTo(expected)
                cancelAndIgnoreRemainingEvents()
            }

            verify(useCases.createRequestToken).invoke()
            verify(useCases.sessionId).invoke()
        }

    @Test
    fun `GIVEN (navigated from Deeplink getArgs succeeds and event is CreateSessionId) WHEN (useCase createSessionId succeeds) THEN (should update bioSuccess state = true and trigger event GetAccount)`() =
        runTest {
            val savedStateHandle =
                SavedStateHandle(mapOf(NavArgument.APPROVED to FAKE_QUERY_PARAMS))

            whenever(useCases.extractQueryArguments(FAKE_QUERY_PARAMS)).thenReturn(fakeQueryArgs)
            whenever(useCases.sessionId()).thenReturn(flowOf(""))
            whenever(useCases.createSessionId(fakeLoginRequest)).thenReturn(fakeSessionId.right())
            whenever(useCases.getUserAccount()).thenReturn(fakeUserAccount.right())

            val viewModel = buildViewModel(savedStateHandle)

            viewModel.state.onEach { println("<-- $it") }.test {
                Truth.assertThat(awaitItem()).isEqualTo(initialState)
                Truth.assertThat(awaitItem().isLoading).isTrue()
                Truth.assertThat(awaitItem().bioSuccess).isTrue()
                cancelAndIgnoreRemainingEvents()
            }

            verify(useCases.extractQueryArguments).invoke(any())
            verify(useCases.sessionId).invoke()
            verify(useCases.createSessionId).invoke(any())
            verify(useCases.getUserAccount).invoke()
        }

    @Test
    fun `GIVEN (navigated from Deeplink getArgs succeeds and event is CreateSessionId) WHEN (useCase createSessionId fails) THEN (should update appError state = AppError)`() =
        runTest {
            val savedStateHandle =
                SavedStateHandle(mapOf(NavArgument.APPROVED to FAKE_QUERY_PARAMS))

            whenever(useCases.extractQueryArguments(FAKE_QUERY_PARAMS)).thenReturn(fakeQueryArgs)
            whenever(useCases.sessionId()).thenReturn(flowOf(""))
            whenever(useCases.createSessionId(fakeLoginRequest)).thenReturn(fakeUnknownAppError.left())

            val viewModel = buildViewModel(savedStateHandle)

            viewModel.state.onEach { println("<-- $it") }.test {
                Truth.assertThat(awaitItem()).isEqualTo(initialState)
                Truth.assertThat(awaitItem().isLoading).isTrue()
                Truth.assertThat(awaitItem().appError).isEqualTo(fakeUnknownAppError)
                cancelAndIgnoreRemainingEvents()
            }

            verify(useCases.extractQueryArguments).invoke(any())
            verify(useCases.sessionId).invoke()
            verify(useCases.createSessionId).invoke(any())
        }


    @Test
    fun `GIVEN (navigated from Deeplink and event is GetAccount) WHEN (useCase getUserAccount succeeds) THEN (should update destination state = MediaCatalog)`() =
        runTest {
            val savedStateHandle =
                SavedStateHandle(mapOf(NavArgument.APPROVED to FAKE_QUERY_PARAMS))

            whenever(useCases.extractQueryArguments(FAKE_QUERY_PARAMS)).thenReturn(fakeQueryArgs)
            whenever(useCases.sessionId()).thenReturn(flowOf(""))
            whenever(useCases.createSessionId(fakeLoginRequest)).thenReturn(fakeSessionId.right())
            whenever(useCases.getUserAccount()).thenReturn(fakeUserAccount.right())

            val viewModel = buildViewModel(savedStateHandle)

            viewModel.state.onEach { println("<-- $it") }.test {
                Truth.assertThat(awaitItem()).isEqualTo(initialState)
                Truth.assertThat(awaitItem().isLoading).isTrue()
                Truth.assertThat(awaitItem().bioSuccess).isTrue()
                Truth.assertThat(awaitItem().isLoading).isFalse()
                Truth.assertThat(awaitItem().isLoading).isTrue()
                Truth.assertThat(awaitItem().destination).isEqualTo(MediaNavigation.MediaCatalog)
                cancelAndIgnoreRemainingEvents()
            }

            verify(useCases.extractQueryArguments).invoke(any())
            verify(useCases.sessionId).invoke()
            verify(useCases.createSessionId).invoke(any())
            verify(useCases.getUserAccount).invoke()
        }

    @Test
    fun `GIVEN (navigated from Deeplink and event is GetAccount) WHEN (useCase getUserAccount fails) THEN (should update appError state = AppError)`() =
        runTest {
            val savedStateHandle =
                SavedStateHandle(mapOf(NavArgument.APPROVED to FAKE_QUERY_PARAMS))

            whenever(useCases.extractQueryArguments(FAKE_QUERY_PARAMS)).thenReturn(fakeQueryArgs)
            whenever(useCases.sessionId()).thenReturn(flowOf(""))
            whenever(useCases.createSessionId(fakeLoginRequest)).thenReturn(fakeSessionId.right())
            whenever(useCases.getUserAccount()).thenReturn(fakeUnknownAppError.left())

            val viewModel = buildViewModel(savedStateHandle)

            viewModel.state.onEach { println("<-- $it") }.test {
                Truth.assertThat(awaitItem()).isEqualTo(initialState)
                Truth.assertThat(awaitItem().isLoading).isTrue()
                Truth.assertThat(awaitItem().bioSuccess).isTrue()
                Truth.assertThat(awaitItem().isLoading).isFalse()
                Truth.assertThat(awaitItem().isLoading).isTrue()
                Truth.assertThat(awaitItem().appError).isEqualTo(fakeUnknownAppError)
                cancelAndIgnoreRemainingEvents()
            }

            verify(useCases.extractQueryArguments).invoke(any())
            verify(useCases.sessionId).invoke()
            verify(useCases.createSessionId).invoke(any())
            verify(useCases.getUserAccount).invoke()
        }

    @Test
    fun `GIVEN (session already exists) WHEN (useCase sessionId collect succeeds) THEN (should update sessionExists state = true which launches biometrics prompt)`() =
        runTest {
            val savedStateHandle = SavedStateHandle()

            whenever(useCases.sessionId()).thenReturn(flowOf(FAKE_SESSION_ID))

            val viewModel = buildViewModel(savedStateHandle)

            viewModel.state.onEach { println("<-- $it") }.test {
                Truth.assertThat(awaitItem()).isEqualTo(initialState)
                Truth.assertThat(awaitItem().sessionExists).isTrue()
                cancelAndIgnoreRemainingEvents()
            }

            verify(useCases.sessionId).invoke()

        }

    @Test
    fun `GIVEN (session already exists) WHEN (useCase sessionId collect fails) THEN (should update appError state = AppError)`() =
        runTest {
            val savedStateHandle = SavedStateHandle()
            val expected = AppError.Message(AppErrorCode.NOT_FOUND, "Unknown error", null)

            whenever(useCases.sessionId()).thenReturn(flow { throw expected })

            val viewModel = buildViewModel(savedStateHandle)

            viewModel.state.onEach { println("<-- $it") }.test {
                Truth.assertThat(awaitItem()).isEqualTo(initialState)
                Truth.assertThat(awaitItem().appError).isEqualTo(expected)
                cancelAndIgnoreRemainingEvents()
            }
            verify(useCases.sessionId).invoke()
        }


    private fun buildViewModel(savedStateHandle: SavedStateHandle): LoginViewModel =
        LoginViewModel(savedStateHandle, useCases)
}