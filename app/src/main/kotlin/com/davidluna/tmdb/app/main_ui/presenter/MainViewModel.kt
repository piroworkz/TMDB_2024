package com.davidluna.tmdb.app.main_ui.presenter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.davidluna.tmdb.core_domain.entities.ContentKind
import com.davidluna.tmdb.core_domain.entities.UserAccount
import com.davidluna.tmdb.core_domain.entities.errors.AppError
import com.davidluna.tmdb.core_domain.entities.errors.AppErrorCode.UNKNOWN
import com.davidluna.tmdb.core_domain.entities.errors.toAppError
import com.davidluna.tmdb.core_domain.usecases.datastore.CloseSessionUseCase
import com.davidluna.tmdb.core_domain.usecases.datastore.SaveContentKindUseCase
import com.davidluna.tmdb.core_domain.usecases.datastore.UserAccountUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val userAccountUseCase: UserAccountUseCase,
    private val saveContentKindUseCase: SaveContentKindUseCase,
    private val closeSessionUseCase: CloseSessionUseCase
) : ViewModel() {

    private val _state: MutableStateFlow<MainState> = MutableStateFlow(MainState())
    val state: StateFlow<MainState> = _state.asStateFlow()

    init {
        initViewModel()
    }

    data class MainState(
        val loading: Boolean = false,
        val appError: AppError? = null,
        val user: com.davidluna.tmdb.core_domain.entities.UserAccount? = null,
        val closeSession: Boolean = false,
    )

    fun sendEvent(event: MainEvent) {
        when (event) {
            is MainEvent.OnCloseSession -> closeSession()
            is MainEvent.SetContentKind -> setContentKind(event.mediaType)
            is MainEvent.SetAppError -> setAppError(event.appError)
        }
    }

    private fun setAppError(appError: AppError?) {
        _state.update { it.copy(appError = appError) }
    }

    private fun initViewModel() {
        sendEvent(MainEvent.SetContentKind(com.davidluna.tmdb.core_domain.entities.ContentKind.MOVIE))
        collectUser()
    }

    private fun closeSession() {
        viewModelScope.launch {
            if (closeSessionUseCase()) {
                _state.update { it.copy(closeSession = true) }
            } else {
                sendEvent(
                    MainEvent.SetAppError(
                        AppError.Message(
                            UNKNOWN,
                            "Error closing session, please try again later"
                        )
                    )
                )
            }
        }
    }

    private fun setContentKind(contentKind: com.davidluna.tmdb.core_domain.entities.ContentKind) = run {
        saveContentKindUseCase(contentKind)
    }

    private fun collectUser() {
        viewModelScope.launch {
            userAccountUseCase()
                .catch { error ->
                    _state.update { it.copy(appError = error.toAppError()) }
                }
                .collect { user ->
                    if (user.id != 0) {
                        _state.update { it.copy(user = user) }
                    }
                }
        }
    }

    private fun run(action: suspend CoroutineScope.() -> Unit) {
        viewModelScope.launch {
            try {
                _state.update { it.copy(loading = true) }
                action()
            } catch (e: Exception) {
                _state.update { it.copy(loading = false) }
                e.printStackTrace()
            } finally {
                _state.update { it.copy(loading = false) }
            }
        }
    }

}


