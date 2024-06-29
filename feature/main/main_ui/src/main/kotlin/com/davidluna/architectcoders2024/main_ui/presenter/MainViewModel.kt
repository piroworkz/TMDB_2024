package com.davidluna.architectcoders2024.main_ui.presenter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.davidluna.architectcoders2024.core_domain.core_entities.ContentKind
import com.davidluna.architectcoders2024.core_domain.core_entities.toAppError
import com.davidluna.architectcoders2024.core_domain.core_usecases.datastore.CloseSessionUseCase
import com.davidluna.architectcoders2024.core_domain.core_usecases.datastore.GetContentKindUseCase
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
    private val setContentKindUseCase: SaveContentKindUseCase,
    private val getContentKindUseCase: GetContentKindUseCase,
    private val closeSessionUseCase: CloseSessionUseCase
) : ViewModel() {

    private val _state: MutableStateFlow<MainState> = MutableStateFlow(MainState())
    val state: StateFlow<MainState> = _state.asStateFlow()

    fun sendEvent(event: MainEvent) {
        when (event) {
            is MainEvent.OnCloseSession -> closeSession()
            is MainEvent.SetContentKind -> setContentKind(event.mediaType)
            MainEvent.OnUiReady -> onUiReady()
        }
    }

    private fun onUiReady() {
        sendEvent(MainEvent.SetContentKind(ContentKind.MOVIE))
        collectUser()
        collectContentKind()
    }

    private fun closeSession() {
        viewModelScope.launch {
            _state.update { s -> s.copy(closeSession = closeSessionUseCase()) }

        }
    }

    private fun setContentKind(contentKind: ContentKind) = run {
        setContentKindUseCase(contentKind)
    }

    private fun collectUser() {
        viewModelScope.launch {
            userAccountUseCase()
                .catch { error ->
                    _state.update { it.copy(appError = error.toAppError()) }
                }
                .collect { user ->
                    _state.update { it.copy(user = user) }
                }
        }
    }

    private fun collectContentKind() = viewModelScope.launch {
        getContentKindUseCase()
            .catch { throwable: Throwable ->
                _state.update { s -> s.copy(appError = throwable.toAppError()) }
            }
            .collect { contentKind: ContentKind ->
                _state.update { s -> s.copy(contentKind = contentKind) }
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


