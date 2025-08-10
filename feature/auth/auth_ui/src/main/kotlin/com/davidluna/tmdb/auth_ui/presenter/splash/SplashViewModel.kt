package com.davidluna.tmdb.auth_ui.presenter.splash

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.davidluna.tmdb.auth_domain.usecases.GetSessionUseCase
import com.davidluna.tmdb.auth_domain.usecases.ValidateGuestSessionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val ioDispatcher: CoroutineDispatcher,
    private val isGuestSessionValid: ValidateGuestSessionUseCase,
    private val getSessionUseCase: GetSessionUseCase,
) : ViewModel() {

    val isLoggedIn: MutableState<Boolean?> = mutableStateOf(null)

    fun checkSessionStatus() {
        viewModelScope.launch(ioDispatcher) {
            val session = getSessionUseCase.flow.first()
            val isSessionValid: Boolean = when {
                session == null -> false
                session.isGuest -> isGuestSessionValid(session.expiresAt)
                else -> true
            }
            isLoggedIn.value = isSessionValid
        }
    }
}

