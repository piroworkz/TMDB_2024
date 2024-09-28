package com.davidluna.tmdb.videos_ui.presenter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.davidluna.tmdb.core_domain.entities.ContentKind
import com.davidluna.tmdb.core_domain.entities.errors.AppError
import com.davidluna.tmdb.core_domain.entities.errors.toAppError
import com.davidluna.tmdb.core_domain.usecases.datastore.GetContentKindUseCase
import com.davidluna.tmdb.core_ui.di.MediaId
import com.davidluna.tmdb.videos_domain.usecases.GetVideosUseCase
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
    @MediaId
    private val mediaId: Int,
    private val getVideosUseCase: GetVideosUseCase,
    private val getContentKindUseCase: GetContentKindUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(PlayerState())
    val state = _state.asStateFlow()

    init {
        collectContentKind()
    }

    data class PlayerState(
        val isLoading: Boolean = false,
        val appError: AppError? = null,
        val videos: List<String> = emptyList(),
        val contentKind: com.davidluna.tmdb.core_domain.entities.ContentKind = com.davidluna.tmdb.core_domain.entities.ContentKind.MOVIE,
    )

    private fun collectContentKind() {
        viewModelScope.launch(Dispatchers.IO) {
            getContentKindUseCase()
                .distinctUntilChanged()
                .catch { e -> _state.update { s: PlayerState -> s.copy(appError = e.toAppError()) } }
                .collect { contentKind: com.davidluna.tmdb.core_domain.entities.ContentKind ->
                    _state.update { s: PlayerState ->
                        s.copy(contentKind = contentKind)
                    }
                    getVideos()
                }
        }
    }

    private fun getVideos() = run {
        val from = if (_state.value.contentKind == com.davidluna.tmdb.core_domain.entities.ContentKind.MOVIE) MOVIE else TV
        getVideosUseCase("$from/$mediaId").fold(
            ifLeft = { e: AppError -> _state.update { it.copy(appError = e) } },
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
