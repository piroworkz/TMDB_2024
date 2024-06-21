package com.davidluna.architectcoders2024.app.ui.screens.login

import android.net.Uri
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.davidluna.architectcoders2024.app.data.remote.model.authentication.RemoteAvatar
import com.davidluna.architectcoders2024.app.data.remote.model.authentication.RemoteGravatar
import com.davidluna.architectcoders2024.app.data.remote.model.authentication.RemoteLoginRequest
import com.davidluna.architectcoders2024.app.data.remote.model.authentication.RemoteUserAccountDetail
import com.davidluna.architectcoders2024.app.ui.navigation.destinations.AuthGraph.Login
import com.davidluna.architectcoders2024.app.ui.navigation.safe_args.DefaultArgs.Auth
import com.davidluna.architectcoders2024.app.utils.toAppError
import com.davidluna.architectcoders2024.app.data.repositories.AuthenticationRepository
import com.davidluna.architectcoders2024.app.data.repositories.SessionRepository
import com.davidluna.architectcoders2024.domain.AppError
import com.davidluna.architectcoders2024.domain.requests.LoginRequest
import com.davidluna.architectcoders2024.usecases.session.CreateGuestSessionIdUseCase
import com.davidluna.architectcoders2024.usecases.session.CreateRequestTokenUseCase
import com.davidluna.architectcoders2024.usecases.session.CreateSessionIdUseCase
import com.davidluna.architectcoders2024.usecases.session.GetUserAccountUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val createRequestTokenUseCase: CreateRequestTokenUseCase,
    private val createSessionIdUseCase: CreateSessionIdUseCase,
    private val createGuestSessionIdUseCase: CreateGuestSessionIdUseCase,
    private val getUserAccountUseCase: GetUserAccountUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(State())
    val state = _state.asStateFlow()

    init {
        getArguments()
    }

    data class State(
        val isLoading: Boolean = false,
        val appError: AppError? = null,
        val token: String? = null,
        val intent: Boolean = false,
        val destination: Destination? = null,
    )

    fun sendEvent(event: LoginEvent) {
        when (event) {
            LoginEvent.AskForPermission -> askForPermission()
            LoginEvent.CreateRequestToken -> createRequestToken()
            is LoginEvent.CreateSessionId -> createSessionId(event.requestToken)
            LoginEvent.GetAccount -> getAccount()
            LoginEvent.ResetError -> resetError()
            is LoginEvent.IsLoggedIn -> setIsLoggedIn(event.destination)
            LoginEvent.CreateGuestSession -> createGuestSessionId()
        }
    }

    private fun setIsLoggedIn(destination: Destination?) {
        _state.update { it.copy(destination = destination) }
    }

    private fun resetError() {
        _state.update { it.copy(appError = null) }
    }

    private fun createRequestToken() = run {
        createRequestTokenUseCase().fold(
            ifLeft = { e -> _state.update { it.copy(appError = e) } },
            ifRight = { r ->
                _state.update { s -> s.copy(token = r.requestToken, intent = true) }
            }
        )
    }

    private fun askForPermission() =
        _state.update { it.copy(intent = !it.intent) }

    private fun createSessionId(requestToken: String) = run {
        createSessionIdUseCase(requestToken.request()).fold(
            ifLeft = { e -> _state.update { it.copy(appError = e) } },
            ifRight = { sendEvent(LoginEvent.GetAccount) }
        )
    }

    private fun createGuestSessionId() {
        run {
            createGuestSessionIdUseCase().fold(
                ifLeft = { e -> _state.update { it.copy(appError = e) } },
                ifRight = { sendEvent(LoginEvent.IsLoggedIn()) }
            )
        }
    }

    private fun getAccount() = run {
        getUserAccountUseCase().fold(
            ifLeft = { e -> _state.update { it.copy(appError = e) } },
            ifRight = { sendEvent(LoginEvent.IsLoggedIn()) }
        )
    }

    private fun getArguments() {
        val args = savedStateHandle.get<String>(Auth.name)
        if (args.isNullOrEmpty()) return
        val uri =
            Uri.parse(Login.link.uriPattern?.replace("{${Login.NAME}}", args))
        val approved = uri.getBooleanQueryParameter("approved", false)
        val requestToken = uri.getQueryParameter("request_token")
        if (approved && requestToken != null) {
            sendEvent(LoginEvent.CreateSessionId(requestToken))
        }
    }

    private fun String.request(): LoginRequest = LoginRequest(this)

    private fun run(action: suspend CoroutineScope.() -> Unit) {
        viewModelScope.launch {
            try {
                _state.update { it.copy(isLoading = true) }
                action()
            } catch (e: Exception) {
                _state.update { it.copy(isLoading = false) }
                e.printStackTrace()
            } finally {
                _state.update { it.copy(isLoading = false) }
            }
        }
    }

}



