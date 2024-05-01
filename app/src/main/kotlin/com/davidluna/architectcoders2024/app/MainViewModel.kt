package com.davidluna.architectcoders2024.app

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.davidluna.architectcoders2024.app.data.toAppError
import com.davidluna.architectcoders2024.domain.AppError
import com.davidluna.architectcoders2024.domain.session.UserAccount
import com.davidluna.architectcoders2024.usecases.preferences.UserAccountUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val userAccountUseCase: UserAccountUseCase,
) : ViewModel() {

    private val _state: MutableStateFlow<State> = MutableStateFlow(State())
    val state = _state.asStateFlow()

    init {
        collectUser()
    }

    data class State(
        val loading: Boolean = false,
        val appError: AppError? = null,
        val user: UserAccount? = null
    )


    private fun collectUser() {
        viewModelScope.launch {
            userAccountUseCase()
                .onStart { _state.update { it.copy(loading = true) } }
                .onCompletion { _state.update { it.copy(loading = false) } }
                .catch { error -> _state.update { it.copy(appError = error.toAppError()) } }
                .collect { user -> _state.update { it.copy(user = user) } }
        }
    }

}