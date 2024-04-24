package com.davidluna.architectcoders2024.app.ui.screens.player

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.davidluna.architectcoders2024.app.data.repositories.MovieDetailsRepository
import com.davidluna.architectcoders2024.app.utils.toAppError
import com.davidluna.architectcoders2024.domain.AppError
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class VideoPlayerViewModel(
    private val repository: MovieDetailsRepository,
    private val movieId: Int?
) : ViewModel() {

    private val _state = MutableStateFlow(State())
    val state = _state.asStateFlow()

    init {
        getMovieVideos()
    }

    data class State(
        val isLoading: Boolean = false,
        val appError: AppError? = null,
        val videos: List<String> = emptyList()
    )

    fun resetError() = _state.update { it.copy(appError = null) }

    private fun getMovieVideos() = run {
        movieId ?: return@run
        repository.getMovieVideos(movieId).fold(
            ifLeft = { e -> _state.update { it.copy(appError = e.toAppError()) } },
            ifRight = { r ->
                _state.update { s ->
                    s.copy(
                        videos = r.sortedBy { it.order }.map { it.key })
                }
            }
        )

    }

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