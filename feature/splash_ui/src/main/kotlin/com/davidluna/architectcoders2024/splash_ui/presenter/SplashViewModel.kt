package com.davidluna.architectcoders2024.splash_ui.presenter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.davidluna.architectcoders2024.core_domain.core_entities.toAppError
import com.davidluna.architectcoders2024.core_domain.core_usecases.datastore.SessionIdUseCase
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
    private val sessionIdUseCase: SessionIdUseCase
) : ViewModel() {

    private val _state: MutableStateFlow<SplashState> = MutableStateFlow(SplashState())
    val state = _state.asStateFlow()

    init {
        collectAuth()
    }

    fun sendEvent(event: SplashEvent) {
        when (event) {
            SplashEvent.OnGranted -> setPermissionState()
            SplashEvent.ResetError -> resetError()
            SplashEvent.OnLoggedIn -> setLoggedIn()
            SplashEvent.OnBioFailed -> onFailed()
        }
    }

    private fun onFailed() {
        _state.update {
            it.copy(
                destination = com.davidluna.architectcoders2024.navigation.model.AuthNav.Login(
                    true
                )
            )
        }
    }

    private fun setLoggedIn() {
        _state.update { it.copy(destination = com.davidluna.architectcoders2024.navigation.model.MoviesNavigation.Movies()) }
    }

    private fun resetError() {
        _state.update { it.copy(appError = null) }
    }

    private fun setPermissionState() {
        _state.update { it.copy(isGranted = true) }
    }

    private fun collectAuth() {
        viewModelScope.launch {
            sessionIdUseCase()
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


