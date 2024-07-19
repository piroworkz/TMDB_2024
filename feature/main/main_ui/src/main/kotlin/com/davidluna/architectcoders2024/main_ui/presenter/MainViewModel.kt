package com.davidluna.architectcoders2024.main_ui.presenter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.davidluna.architectcoders2024.core_domain.core_entities.ContentKind
import com.davidluna.architectcoders2024.core_domain.core_entities.UserAccount
import com.davidluna.architectcoders2024.core_domain.core_entities.errors.AppError
import com.davidluna.architectcoders2024.core_domain.core_entities.errors.AppErrorCode.UNKNOWN
import com.davidluna.architectcoders2024.core_domain.core_entities.errors.toAppError
import com.davidluna.architectcoders2024.core_domain.core_usecases.datastore.CloseSessionUseCase
import com.davidluna.architectcoders2024.core_domain.core_usecases.datastore.SaveContentKindUseCase
import com.davidluna.architectcoders2024.core_domain.core_usecases.datastore.UserAccountUseCase
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
        val user: UserAccount? = null,
        val closeSession: Boolean = false,
    )

    fun sendEvent(event: MainEvent) {
        println("<--- $event")
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
        sendEvent(MainEvent.SetContentKind(ContentKind.MOVIE))
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

    private fun setContentKind(contentKind: ContentKind) = run {
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


