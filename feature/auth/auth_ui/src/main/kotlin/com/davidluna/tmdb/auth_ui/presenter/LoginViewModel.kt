package com.davidluna.tmdb.auth_ui.presenter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.davidluna.tmdb.auth_domain.entities.session.LoginRequest
import com.davidluna.tmdb.auth_domain.usecases.LoginViewModelUseCases
import com.davidluna.tmdb.auth_ui.presenter.LoginEvent.GuestButtonCLicked
import com.davidluna.tmdb.auth_ui.presenter.LoginEvent.LaunchBioPrompt
import com.davidluna.tmdb.auth_ui.presenter.LoginEvent.LoginButtonClicked
import com.davidluna.tmdb.auth_ui.presenter.LoginEvent.Navigate
import com.davidluna.tmdb.auth_ui.presenter.LoginEvent.SetAppError
import com.davidluna.tmdb.core_domain.entities.Session
import com.davidluna.tmdb.core_domain.entities.errors.AppError
import com.davidluna.tmdb.core_ui.navigation.destination.AuthNavigation
import com.davidluna.tmdb.core_ui.navigation.destination.Destination
import com.davidluna.tmdb.core_ui.navigation.destination.MediaNavigation.MediaCatalog
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginArgs: AuthNavigation.Login,
    private val usecases: LoginViewModelUseCases,
) : ViewModel() {

    private val _state = MutableStateFlow(LoginState())
    val state = _state.asStateFlow()

    init {
        collectSession()
        getArguments()
    }

    data class LoginState(
        val isLoading: Boolean = false,
        val appError: AppError? = null,
        val userName: String = String(),
        val password: String = String(),
        val session: com.davidluna.tmdb.core_domain.entities.Session? = null,
        val token: String? = null,
        val launchTMDBWeb: Boolean = false,
        val launchBioPrompt: Boolean = false,
        val destination: Destination? = null,
    )

    fun sendEvent(event: LoginEvent) {
        when (event) {
            GuestButtonCLicked -> createGuestSessionId()
            LoginButtonClicked -> createRequestToken()
            is Navigate -> setDestination(event.destination)
            is SetAppError -> setAppError(event.error)
            is LaunchBioPrompt -> setLaunchBioPrompt(event.value)
        }
    }

    private fun setLaunchBioPrompt(value: Boolean) {
        _state.update {
            it.copy(
                launchBioPrompt = value && _state.value.session?.id?.isNotEmpty() == true
            )
        }
    }

    private fun setAppError(error: AppError?) {
        _state.update { it.copy(appError = error) }
    }

    private fun setDestination(destination: Destination?) {
        _state.update { it.copy(destination = destination) }
    }

    private fun createRequestToken() = run {
        setLaunchBioPrompt(false)
        usecases.createRequestToken().fold(
            ifLeft = { e -> _state.update { it.copy(appError = e) } },
            ifRight = { r ->
                _state.update { s -> s.copy(token = r.requestToken, launchTMDBWeb = true) }
            }
        )
    }

    private fun fetchSession(requestToken: String) = run {
        usecases.createSessionId(requestToken.toLoginRequest()).fold(
            ifLeft = { e -> _state.update { it.copy(appError = e) } },
            ifRight = { getAccount() }
        )
    }

    private fun createGuestSessionId() = run {
        val isNotExpired = usecases
            .guestSessionNotExpired
            .invoke(_state.value.session?.guestSession?.expiresAt ?: "")

        if (isNotExpired) {
            setDestination(MediaCatalog())
        } else {
            usecases.createGuestSessionId().fold(
                ifLeft = { e -> _state.update { it.copy(appError = e) } },
                ifRight = {
                    setDestination(MediaCatalog())
                }
            )
        }
    }

    private fun getAccount() = run {
        usecases.getUserAccount().fold(
            ifLeft = { e -> _state.update { it.copy(appError = e) } },
            ifRight = { setDestination(MediaCatalog()) }
        )
    }

    private fun collectSession() {
        viewModelScope.launch {
            val currentSession = usecases.sessionId().firstOrNull()
            _state.update {
                it.copy(
                    session = currentSession,
                    launchBioPrompt = !currentSession?.id.isNullOrEmpty()
                )
            }
        }
    }

    private fun getArguments() {
        if (userApprovedApp()) {
            fetchSession(loginArgs.requestToken)
        }
    }

    private fun userApprovedApp() =
        loginArgs.requestToken.isNotEmpty() && loginArgs.approved

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
