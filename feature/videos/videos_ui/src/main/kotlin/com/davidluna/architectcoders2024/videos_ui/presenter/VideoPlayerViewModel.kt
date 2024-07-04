package com.davidluna.architectcoders2024.videos_ui.presenter

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.davidluna.architectcoders2024.core_domain.core_entities.AppError
import com.davidluna.architectcoders2024.core_domain.core_entities.ContentKind
import com.davidluna.architectcoders2024.core_domain.core_entities.toAppError
import com.davidluna.architectcoders2024.core_domain.core_usecases.datastore.GetContentKindUseCase
import com.davidluna.architectcoders2024.videos_domain.videos_domain_entities.YoutubeVideo
import com.davidluna.architectcoders2024.videos_domain.videos_domain_usecases.GetVideosUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VideoPlayerViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getVideosUseCase: GetVideosUseCase,
    private val getContentKindUseCase: GetContentKindUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(PlayerState())
    val state = _state.asStateFlow()

    init {
        collectContentKind()
    }

    private fun collectContentKind() {
        viewModelScope.launch(Dispatchers.IO) {
            getContentKindUseCase()
                .distinctUntilChanged()
                .catch { e -> _state.update { s: PlayerState -> s.copy(appError = e.toAppError()) } }
                .collect { contentKind: ContentKind ->
                    _state.update { s: PlayerState ->
                        s.copy(contentKind = contentKind)
                    }
                    getVideos()
                }
        }
    }

    private fun getVideos() = run {
        val from = if (_state.value.contentKind == ContentKind.MOVIE) MOVIE else TV
        val movieId =
            savedStateHandle.toRoute<com.davidluna.architectcoders2024.navigation.domain.YoutubeNavigation.Video>().movieId
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
