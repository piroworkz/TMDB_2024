package com.davidluna.architectcoders2024.auth_ui.presenter

import androidx.lifecycle.SavedStateHandle
import com.davidluna.architectcoders2024.auth_domain.auth_domain_usecases.session.CreateGuestSessionIdUseCase
import com.davidluna.architectcoders2024.auth_domain.auth_domain_usecases.session.CreateRequestTokenUseCase
import com.davidluna.architectcoders2024.auth_domain.auth_domain_usecases.session.CreateSessionIdUseCase
import com.davidluna.architectcoders2024.auth_domain.auth_domain_usecases.session.GetUserAccountUseCase
import com.davidluna.architectcoders2024.core_domain.core_usecases.datastore.SessionIdUseCase
import com.davidluna.architectcoders2024.test_shared_framework.rules.CoroutineTestRule
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class LoginViewModelTest {

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    @Mock
    lateinit var savedStateHandle: SavedStateHandle

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


    private val initialState: LoginViewModel.LoginState = LoginViewModel.LoginState()

//    @Test
//    fun `given viewModel init if navigation is from deeplink when getArguments is successful then should send event CreateSessionId`() =
//        runTest {
//            val sessionId = flowOf("sessionId")
//            whenever(savedStateHandle.get<String>(any())).thenReturn("token")
//            whenever(sessionIdUseCase.invoke()).thenReturn(sessionId)
//            whenever(uri.getBooleanQueryParameter(NavArgument.APPROVED, false)).thenReturn(true)
//            whenever(uri.getQueryParameter(NavArgument.REQUEST_TOKEN)).thenReturn("token")
//
//            val viewModel = buildViewModel()
//            val expected = initialState.copy(token = "token")
//
//            viewModel.state.onEach { println("<-- $it") }.test {
////            Truth.assertThat(awaitItem()).isEqualTo(initialState)
////            Truth.assertThat(awaitItem().isLoading).isTrue()
//                awaitComplete()
//                cancel()
//            }
//        }


    private fun buildViewModel(): LoginViewModel {
        return LoginViewModel(
            savedStateHandle,
            createRequestTokenUseCase,
            createSessionIdUseCase,
            createGuestSessionIdUseCase,
            getUserAccountUseCase,
            sessionIdUseCase
        )
    }
}