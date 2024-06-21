package com.davidluna.architectcoders2024.app.ui.screens.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.davidluna.architectcoders2024.app.data.toAppError
import com.davidluna.architectcoders2024.app.ui.navigation.destinations.AuthNav
import com.davidluna.architectcoders2024.app.ui.navigation.destinations.Destination
import com.davidluna.architectcoders2024.app.ui.navigation.destinations.MoviesNavigation
import com.davidluna.architectcoders2024.domain.AppError
import com.davidluna.architectcoders2024.usecases.preferences.SessionIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val sessionId: SessionIdUseCase
) : ViewModel() {

    private val _state: MutableStateFlow<State> = MutableStateFlow(State())
    val state = _state.asStateFlow()

    init {
        collectAuth()
    }

    data class State(
        val loading: Boolean = false,
        val appError: AppError? = null,
        val destination: Destination? = null,
        val sessionExists: Boolean = false,
        val isGranted: Boolean = false,
    )

    fun sendEvent(event: SplashEvent) {
        when (event) {
            SplashEvent.OnGranted -> setPermissionState()
            SplashEvent.ResetError -> resetError()
            SplashEvent.OnLoggedIn -> setLoggedIn()
            SplashEvent.OnBioFailed -> onFailed()
        }
    }

    private fun onFailed() {
        _state.update { it.copy(destination = AuthNav.Login(true)) }
    }

    private fun setLoggedIn() {
        _state.update { it.copy(destination = MoviesNavigation.Movies()) }
    }

    private fun resetError() {
        _state.update { it.copy(appError = null) }
    }

    private fun setPermissionState() {
        _state.update { it.copy(isGranted = true) }
    }

    private fun collectAuth() {
        viewModelScope.launch {
            sessionId()
                .onStart { _state.update { it.copy(loading = true) } }
                .catch { e ->
                    _state.update {
                        it.copy(
                            appError = e.toAppError(),
                            loading = false
                        )
                    }
                }
                .collect { id ->
                    _state.update {
                        it.copy(
                            sessionExists = id.isNotEmpty(),
                            loading = false
                        )
                    }
                }
        }
    }
}
