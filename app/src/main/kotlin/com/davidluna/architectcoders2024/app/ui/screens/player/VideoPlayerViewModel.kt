package com.davidluna.architectcoders2024.app.ui.screens.player

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.davidluna.architectcoders2024.app.ui.navigation.safe_args.Args
import com.davidluna.architectcoders2024.app.ui.navigation.safe_args.ContentType
import com.davidluna.architectcoders2024.domain.AppError
import com.davidluna.architectcoders2024.usecases.movies.GetMovieVideosUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VideoPlayerViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getVideosUseCase: GetMovieVideosUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(State())
    val state = _state.asStateFlow()

    init {
        savedStateHandle.apply {
            get<ContentType>(Args.Type.name)?.let { type ->
                get<Int>(Args.MovieId.name)?.let { getVideos(it, type) }

            }
        }

    }

    data class State(
        val isLoading: Boolean = false,
        val appError: AppError? = null,
        val videos: List<String> = emptyList()
    )

    private fun getVideos(movieId: Int, contentType: ContentType) = run {
        val from = if (contentType == ContentType.MOVIE) MOVIE else TV
        getVideosUseCase("$from/$movieId").fold(
            ifLeft = { e -> _state.update { it.copy(appError = e) } },
            ifRight = { r ->
                _state.update { s ->
                    s.copy(videos = r.sortedBy { it.order }.map { it.key })
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

    companion object {
        private const val MOVIE = "movie"
        private const val TV = "tv"
    }
}