package com.davidluna.architectcoders2024.auth_ui.presenter

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.davidluna.architectcoders2024.auth_domain.auth_domain_entities.session.LoginRequest
import com.davidluna.architectcoders2024.auth_domain.auth_domain_usecases.session.LoginViewModelUseCases
import com.davidluna.architectcoders2024.auth_ui.presenter.LoginEvent.GuestButtonCLicked
import com.davidluna.architectcoders2024.auth_ui.presenter.LoginEvent.LaunchBioPrompt
import com.davidluna.architectcoders2024.auth_ui.presenter.LoginEvent.LoginButtonClicked
import com.davidluna.architectcoders2024.auth_ui.presenter.LoginEvent.Navigate
import com.davidluna.architectcoders2024.auth_ui.presenter.LoginEvent.SetAppError
import com.davidluna.architectcoders2024.core_domain.core_entities.Session
import com.davidluna.architectcoders2024.core_domain.core_entities.errors.AppError
import com.davidluna.architectcoders2024.core_domain.core_entities.labels.NavArgument
import com.davidluna.architectcoders2024.navigation.domain.args.DefaultArgs.Auth.defaultValue
import com.davidluna.architectcoders2024.navigation.domain.destination.Destination
import com.davidluna.architectcoders2024.navigation.domain.destination.MediaNavigation.MediaCatalog
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
    private val savedStateHandle: SavedStateHandle,
    private val usecases: LoginViewModelUseCases
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
        val session: Session? = null,
        val token: String? = null,
        val launchTMDBWeb: Boolean = false,
        val launchBioPrompt: Boolean = false,
        val destination: Destination? = null
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
        _state.update { it.copy(launchBioPrompt = value) }
    }

    private fun setAppError(error: AppError?) {
        _state.update { it.copy(appError = error) }
    }

    private fun setDestination(destination: Destination?) {
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
            setDestination(MediaCatalog)
        } else {
            usecases.createGuestSessionId().fold(
                ifLeft = { e -> _state.update { it.copy(appError = e) } },
                ifRight = {
                    setDestination(MediaCatalog)
                }
            )
        }
    }

    private fun getAccount() = run {
        usecases.getUserAccount().fold(
            ifLeft = { e -> _state.update { it.copy(appError = e) } },
            ifRight = { setDestination(MediaCatalog) }
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
        val args: String = savedStateHandle.get<String>(NavArgument.APPROVED) ?: defaultValue
        if (args != defaultValue) {
            usecases.extractQueryArguments(args).apply {
                if (approved) fetchSession(requestToken)
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



