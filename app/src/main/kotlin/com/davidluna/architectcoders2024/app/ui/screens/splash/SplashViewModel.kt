package com.davidluna.architectcoders2024.app.ui.screens.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.davidluna.architectcoders2024.app.ui.navigation.destinations.AuthGraph
import com.davidluna.architectcoders2024.app.ui.navigation.destinations.Destination
import com.davidluna.architectcoders2024.app.ui.navigation.destinations.MoviesGraph
import com.davidluna.architectcoders2024.app.ui.screens.splash.AnimationState.START
import com.davidluna.architectcoders2024.app.data.repositories.SessionRepository
import com.davidluna.architectcoders2024.domain.AppError
import com.davidluna.architectcoders2024.domain.toAppError
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SplashViewModel(
    private val local: SessionRepository
) : ViewModel() {

    private val _state = MutableStateFlow(State())
    val state = _state.asStateFlow()

    init {
        collectAuth()
    }

    data class State(
        val loading: Boolean = false,
        val appError: AppError? = null,
        val destination: Destination? = null,
        val animationState: AnimationState = START
    )

    fun setAnimationState(state: AnimationState) {
        _state.update { it.copy(animationState = state) }
    }

    private fun collectAuth() {
        viewModelScope.launch {
            local.auth
                .catch { e ->
                    e.printStackTrace()
                    _state.update { it.copy(appError = e.toAppError()) }
                }
                .collect { a ->
                    if (a.sessionId.isNotEmpty()) {
                        _state.update { it.copy(destination = MoviesGraph.Home) }
                    } else {
                        _state.update { it.copy(destination = AuthGraph.Login) }
                    }
                }
        }
    }
}