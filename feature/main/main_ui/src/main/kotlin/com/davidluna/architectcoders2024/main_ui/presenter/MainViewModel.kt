package com.davidluna.architectcoders2024.main_ui.presenter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.davidluna.architectcoders2024.core_domain.core_entities.ContentKind
import com.davidluna.architectcoders2024.core_domain.core_entities.toAppError
import com.davidluna.architectcoders2024.core_domain.core_usecases.datastore.GetContentKindUseCase
import com.davidluna.architectcoders2024.core_domain.core_usecases.datastore.SaveContentKindUseCase
import com.davidluna.architectcoders2024.core_domain.core_usecases.datastore.UserAccountUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val userAccountUseCase: UserAccountUseCase,
    private val setContentKindUseCase: SaveContentKindUseCase,
    private val getContentKindUseCase: GetContentKindUseCase
) : ViewModel() {

    private val _state: MutableStateFlow<MainState> = MutableStateFlow(MainState())
    val state: StateFlow<MainState>
        get() {
            collectUser()
            collectContentKind()
            return _state.asStateFlow()
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
            .onStart {
                _state.update { s -> s.copy(loading = true) }
            }
            .catch { throwable: Throwable ->
                _state.update { s -> s.copy(appError = throwable.toAppError(), loading = false) }
            }
            .collect { contentKind: ContentKind ->
                _state.update { s -> s.copy(contentKind = contentKind, loading = false) }
            }
    }

    fun setContentKind(contentKind: ContentKind) = run {
        setContentKindUseCase(contentKind)
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
