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
import com.davidluna.architectcoders2024.core_domain.core_entities.AppError
import com.davidluna.architectcoders2024.core_domain.core_entities.labels.NavArgument
import com.davidluna.architectcoders2024.core_domain.core_entities.toAppError
import com.davidluna.architectcoders2024.core_domain.core_usecases.datastore.SessionIdUseCase
import com.davidluna.architectcoders2024.navigation.domain.AuthNav
import com.davidluna.architectcoders2024.navigation.domain.Destination
import com.davidluna.architectcoders2024.navigation.domain.MediaNavigation.Movies
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val createRequestTokenUseCase: CreateRequestTokenUseCase,
    private val createSessionIdUseCase: CreateSessionIdUseCase,
    private val createGuestSessionIdUseCase: CreateGuestSessionIdUseCase,
    private val getUserAccountUseCase: GetUserAccountUseCase,
    private val sessionIdUseCase: SessionIdUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(LoginState())
    val state = _state.asStateFlow()

    init {
        getArguments()
        collectAuth()
    }

    data class LoginState(
        val isLoading: Boolean = false,
        val appError: AppError? = null,
        val token: String? = null,
        val intent: Boolean = false,
        val destination: Destination? = null,
        val sessionExists: Boolean = false
    )

    fun sendEvent(event: LoginEvent) {
        when (event) {
            AskForPermission -> askForPermission()
            CreateRequestToken -> createRequestToken()
            is CreateSessionId -> createSessionId(event.requestToken)
            GetAccount -> getAccount()
            is IsLoggedIn -> setIsLoggedIn(event.destination)
            CreateGuestSession -> createGuestSessionId()
            is LoginEvent.SetError -> setAppError(event.error)
        }
    }

    private fun setAppError(error: AppError?) {
        _state.update { it.copy(appError = error) }
    }

    private fun setIsLoggedIn(destination: Destination?) {
        _state.update { it.copy(destination = destination) }
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
        createSessionIdUseCase(requestToken.toLoginRequest()).fold(
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

    private fun collectAuth() {
        viewModelScope.launch {
            sessionIdUseCase()
                .catch { e -> _state.update { it.copy(appError = e.toAppError()) } }
                .collect { id ->
                    _state.update { it.copy(sessionExists = id.isNotEmpty()) }
                }
        }
    }

    private fun getArguments() {
        val args =
            savedStateHandle.get<String>(AuthNav.Login.NAME)
        if (args.isNullOrEmpty()) return
        val uri =
            Uri.parse(AuthNav.Login.link.uriPattern?.replace("{${AuthNav.Login.NAME}}", args))
        val approved = uri.getBooleanQueryParameter(NavArgument.APPROVED, false)
        val requestToken = uri.getQueryParameter(NavArgument.REQUEST_TOKEN)
        if (approved && requestToken != null) {
            sendEvent(CreateSessionId(requestToken))
        }
    }

    private fun String.toLoginRequest(): LoginRequest = LoginRequest(this)

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



