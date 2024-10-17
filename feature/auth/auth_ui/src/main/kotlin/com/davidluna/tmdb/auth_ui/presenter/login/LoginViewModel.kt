package com.davidluna.tmdb.auth_ui.presenter.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.davidluna.tmdb.auth_domain.entities.session.LoginRequest
import com.davidluna.tmdb.auth_domain.usecases.LoginViewModelUseCases
import com.davidluna.tmdb.auth_ui.presenter.login.LoginEvent.GuestButtonCLicked
import com.davidluna.tmdb.auth_ui.presenter.login.LoginEvent.LoginButtonClicked
import com.davidluna.tmdb.auth_ui.presenter.login.LoginEvent.Navigate
import com.davidluna.tmdb.auth_ui.presenter.login.LoginEvent.SetAppError
import com.davidluna.tmdb.core_domain.entities.errors.AppError
import com.davidluna.tmdb.core_ui.navigation.destination.AuthNavigation
import com.davidluna.tmdb.core_ui.navigation.destination.Destination
import com.davidluna.tmdb.core_ui.navigation.destination.MediaNavigation.MediaCatalog
import com.davidluna.tmdb.core_ui.onInit
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginArgs: AuthNavigation.Login,
    private val usecases: LoginViewModelUseCases,
) : ViewModel() {

    private val _state = MutableStateFlow(LoginState())
    val state = _state.onInit(viewModelScope, ::getArguments)

    data class LoginState(
        val isLoading: Boolean = false,
        val appError: AppError? = null,
        val token: String? = null,
        val launchTMDBWeb: Boolean = false,
        val destination: Destination? = null,
    )

    fun onEvent(event: LoginEvent) {
        when (event) {
            GuestButtonCLicked -> createGuestSessionId()
            LoginButtonClicked -> createRequestToken()
            is Navigate -> setDestination(event.destination)
            is SetAppError -> setAppError(event.error)
        }
    }

    private fun setAppError(error: AppError?) {
        _state.update { it.copy(appError = error) }
    }

    private fun setDestination(destination: Destination?) {
        _state.update { it.copy(destination = destination) }
    }

    private fun createRequestToken() = runCoroutine {
        usecases.createRequestToken()
            .fold(ifLeft = { e -> _state.update { it.copy(appError = e) } }, ifRight = { r ->
                _state.update { s ->
                    s.copy(token = r.requestToken, launchTMDBWeb = true)
                }
            })
    }

    private fun fetchSession(requestToken: String) = runCoroutine {
        usecases.createSessionId(requestToken.toLoginRequest())
            .fold(ifLeft = { e -> _state.update { it.copy(appError = e) } },
                ifRight = { getAccount() })
    }

    private fun createGuestSessionId() = runCoroutine {
        usecases.createGuestSessionId()
            .fold(ifLeft = { e -> _state.update { it.copy(appError = e) } },
                ifRight = { onEvent(Navigate(MediaCatalog())) })
    }

    private fun getAccount() = runCoroutine {
        usecases.getUserAccount().fold(ifLeft = { e -> _state.update { it.copy(appError = e) } },
            ifRight = { onEvent(Navigate(MediaCatalog())) })
    }

    private fun getArguments() {
        if (userApprovedApp()) {
            fetchSession(loginArgs.requestToken)
        }
    }

    private fun userApprovedApp() = loginArgs.requestToken.isNotEmpty() && loginArgs.approved

    private fun String.toLoginRequest(): LoginRequest = LoginRequest(this)

    private fun runCoroutine(action: suspend CoroutineScope.() -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
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
