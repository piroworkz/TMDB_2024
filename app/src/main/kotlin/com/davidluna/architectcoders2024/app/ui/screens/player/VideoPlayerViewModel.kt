package com.davidluna.architectcoders2024.app.ui.screens.player

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.davidluna.architectcoders2024.app.ui.navigation.destinations.MoviesNavigation
import com.davidluna.architectcoders2024.domain.AppError
import com.davidluna.architectcoders2024.domain.ContentKind
import com.davidluna.architectcoders2024.domain.responses.movies.YoutubeVideo
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
    private val savedStateHandle: SavedStateHandle,
    private val getVideosUseCase: GetMovieVideosUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(State())
    val state = _state.asStateFlow()

    init {
        getVideos()
    }

    data class State(
        val isLoading: Boolean = false,
        val appError: AppError? = null,
        val videos: List<String> = emptyList(),
        val contentKind: ContentKind = ContentKind.MOVIE
    )

    fun setContentKind(contentKind: ContentKind) =
        _state.update { s -> s.copy(contentKind = contentKind) }

    private fun getVideos() = run {
        val from = if (_state.value.contentKind == ContentKind.MOVIE) MOVIE else TV
        val movieId = savedStateHandle.toRoute<MoviesNavigation.VideoPlayer>().movieId
        getVideosUseCase("$from/$movieId").fold(
            ifLeft = { e: AppError ->
                _state.update { it.copy(appError = e) }
            },
            ifRight = { r: List<YoutubeVideo> ->
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
