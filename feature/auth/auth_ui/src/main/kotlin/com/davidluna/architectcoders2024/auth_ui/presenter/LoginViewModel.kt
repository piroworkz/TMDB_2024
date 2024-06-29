package com.davidluna.architectcoders2024.auth_ui.presenter

import android.net.Uri
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.davidluna.architectcoders2024.auth_domain.auth_domain_entities.session.LoginRequest
import com.davidluna.architectcoders2024.auth_domain.auth_domain_usecases.session.CreateGuestSessionIdUseCase
import com.davidluna.architectcoders2024.auth_domain.auth_domain_usecases.session.CreateRequestTokenUseCase
import com.davidluna.architectcoders2024.auth_domain.auth_domain_usecases.session.CreateSessionIdUseCase
import com.davidluna.architectcoders2024.auth_domain.auth_domain_usecases.session.GetUserAccountUseCase
import com.davidluna.architectcoders2024.auth_ui.presenter.LoginEvent.AskForPermission
import com.davidluna.architectcoders2024.auth_ui.presenter.LoginEvent.CreateGuestSession
import com.davidluna.architectcoders2024.auth_ui.presenter.LoginEvent.CreateRequestToken
import com.davidluna.architectcoders2024.auth_ui.presenter.LoginEvent.CreateSessionId
import com.davidluna.architectcoders2024.auth_ui.presenter.LoginEvent.GetAccount
import com.davidluna.architectcoders2024.auth_ui.presenter.LoginEvent.IsLoggedIn
import com.davidluna.architectcoders2024.auth_ui.presenter.LoginEvent.OnUiReady
import com.davidluna.architectcoders2024.auth_ui.presenter.LoginEvent.ResetError
import com.davidluna.architectcoders2024.navigation.domain.AuthNav
import com.davidluna.architectcoders2024.navigation.domain.Destination
import com.davidluna.architectcoders2024.navigation.domain.MoviesNavigation.Movies
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

    private val _state = MutableStateFlow(LoginState())
    val state = _state.asStateFlow()

    fun sendEvent(event: LoginEvent) {
        when (event) {
            AskForPermission -> askForPermission()
            CreateRequestToken -> createRequestToken()
            is CreateSessionId -> createSessionId(event.requestToken)
            GetAccount -> getAccount()
            ResetError -> resetError()
            is IsLoggedIn -> setIsLoggedIn(event.destination)
            CreateGuestSession -> createGuestSessionId()
            OnUiReady -> getArguments()
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
            ifRight = { sendEvent(GetAccount) }
        )
    }

    private fun createGuestSessionId() {
        run {
            createGuestSessionIdUseCase().fold(
                ifLeft = { e -> _state.update { it.copy(appError = e) } },
                ifRight = { sendEvent(IsLoggedIn(Movies())) }
            )
        }
    }

    private fun getAccount() = run {
        getUserAccountUseCase().fold(
            ifLeft = { e -> _state.update { it.copy(appError = e) } },
            ifRight = { sendEvent(IsLoggedIn(Movies())) }
        )
    }

    private fun getArguments() {
        val args =
            savedStateHandle.get<String>(AuthNav.Login.NAME)
        if (args.isNullOrEmpty()) return
        val uri =
            Uri.parse(AuthNav.Login.link.uriPattern?.replace("{${AuthNav.Login.NAME}}", args))
        val approved = uri.getBooleanQueryParameter("approved", false)
        val requestToken = uri.getQueryParameter("request_token")
        if (approved && requestToken != null) {
            sendEvent(CreateSessionId(requestToken))
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



