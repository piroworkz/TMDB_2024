package com.davidluna.architectcoders2024.auth_ui.presenter

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.davidluna.architectcoders2024.auth_domain.auth_domain_entities.session.LoginRequest
import com.davidluna.architectcoders2024.auth_domain.auth_domain_usecases.session.LoginViewModelUseCases
import com.davidluna.architectcoders2024.auth_ui.presenter.LoginEvent.CreateGuestSession
import com.davidluna.architectcoders2024.auth_ui.presenter.LoginEvent.CreateSessionId
import com.davidluna.architectcoders2024.auth_ui.presenter.LoginEvent.GetAccount
import com.davidluna.architectcoders2024.auth_ui.presenter.LoginEvent.IsLoggedIn
import com.davidluna.architectcoders2024.auth_ui.presenter.LoginEvent.OnLoginClicked
import com.davidluna.architectcoders2024.core_domain.core_entities.errors.AppError
import com.davidluna.architectcoders2024.core_domain.core_entities.errors.toAppError
import com.davidluna.architectcoders2024.core_domain.core_entities.labels.NavArgument
import com.davidluna.architectcoders2024.navigation.domain.destination.Destination
import com.davidluna.architectcoders2024.navigation.domain.destination.MediaNavigation.MediaCatalog
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
    private val usecases: LoginViewModelUseCases
) : ViewModel() {

    private val _state = MutableStateFlow(LoginState())
    val state = _state.asStateFlow()

    init {
        collectAuth()
        getArguments()
    }

    data class LoginState(
        val isLoading: Boolean = false,
        val appError: AppError? = null,
        val token: String? = null,
        val launchTMDBWeb: Boolean = false,
        val destination: Destination? = null,
        val sessionExists: Boolean = false,
        val bioSuccess: Boolean = false,
    )

    fun sendEvent(event: LoginEvent) {
        when (event) {
            CreateGuestSession -> createGuestSessionId()
            is CreateSessionId -> createSessionId(event.requestToken)
            GetAccount -> getAccount()
            is IsLoggedIn -> setIsLoggedIn(event.destination)
            OnLoginClicked -> createRequestToken()
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
        usecases.createRequestToken().fold(
            ifLeft = { e -> _state.update { it.copy(appError = e) } },
            ifRight = { r ->
                _state.update { s -> s.copy(token = r.requestToken, launchTMDBWeb = true) }
            }
        )
    }

    private fun createSessionId(requestToken: String) = run {
        usecases.createSessionId(requestToken.toLoginRequest()).fold(
            ifLeft = { e -> _state.update { it.copy(appError = e) } },
            ifRight = {
                _state.update { it.copy(bioSuccess = true) }
                sendEvent(GetAccount)
            }
        )
    }

    private fun createGuestSessionId() {
        run {
            usecases.createGuestSessionId().fold(
                ifLeft = { e -> _state.update { it.copy(appError = e) } },
                ifRight = {
                    sendEvent(IsLoggedIn(MediaCatalog))
                    _state.update { it.copy(bioSuccess = true) }
                }
            )
        }
    }

    private fun getAccount() = run {
        usecases.getUserAccount().fold(
            ifLeft = { e -> _state.update { it.copy(appError = e) } },
            ifRight = { sendEvent(IsLoggedIn(MediaCatalog)) }
        )
    }

    private fun collectAuth() {
        viewModelScope.launch {
            usecases.sessionId()
                .catch { e -> _state.update { it.copy(appError = e.toAppError()) } }
                .collect { id -> _state.update { it.copy(sessionExists = id.isNotEmpty()) } }
        }
    }

    private fun getArguments() {
        savedStateHandle.get<String>(NavArgument.APPROVED)?.let {
            usecases.extractQueryArguments(it).apply {
                if (approved) {
                    sendEvent(CreateSessionId(requestToken))
                }
            }
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



