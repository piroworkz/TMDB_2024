package com.davidluna.architectcoders2024.media_ui.presenter.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import androidx.paging.PagingData
import com.davidluna.architectcoders2024.core_domain.core_entities.AppError
import com.davidluna.architectcoders2024.core_domain.core_entities.ContentKind
import com.davidluna.architectcoders2024.core_domain.core_entities.toAppError
import com.davidluna.architectcoders2024.core_domain.core_usecases.datastore.GetContentKindUseCase
import com.davidluna.architectcoders2024.core_ui.log
import com.davidluna.architectcoders2024.media_ui.presenter.paging.asPagingFlow
import com.davidluna.architectcoders2024.navigation.domain.Destination
import com.davidluna.architectcoders2024.navigation.domain.MoviesNavigation
import com.davidluna.media_domain.media_domain_entities.Cast
import com.davidluna.media_domain.media_domain_entities.Details
import com.davidluna.media_domain.media_domain_entities.Media
import com.davidluna.media_domain.media_domain_usecases.GetContentUseCase
import com.davidluna.media_domain.media_domain_usecases.GetMovieCastUseCase
import com.davidluna.media_domain.media_domain_usecases.GetMovieDetailsUseCase
import com.davidluna.media_domain.media_domain_usecases.GetMovieImagesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getMovieDetails: GetMovieDetailsUseCase,
    private val getMovieImagesUseCase: GetMovieImagesUseCase,
    private val getMovieCastUseCase: GetMovieCastUseCase,
    private val getContent: GetContentUseCase,
    private val getContentKindUseCase: GetContentKindUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(State())
    val state = _state.asStateFlow()

    data class State(
        val isLoading: Boolean = false,
        val appError: AppError? = null,
        val contentKind: ContentKind? = null,
        val destination: Destination? = null,
        val movieDetail: Details? = null,
        val movieCredits: List<Cast> = emptyList(),
        val images: List<String> = emptyList(),
        val recommendations: Flow<PagingData<Media>> = emptyFlow(),
        val similar: Flow<PagingData<Media>> = emptyFlow(),
    )

    fun sendEvent(event: MovieDetailEvent) {
        when (event) {
            is MovieDetailEvent.OnNavigate -> setDestination(event.destination)
            MovieDetailEvent.ResetError -> resetError()
            MovieDetailEvent.OnViewReady -> collectContentKind()
        }
    }

    private fun getArgs(savedStateHandle: SavedStateHandle) {
        savedStateHandle.toRoute<MoviesNavigation.Detail>()
            .apply {
                _state.value.contentKind?.let { kind ->
                    kind.name.log("contentKind")
                    movieId.toString().log("movieId")
                    fetchData(movieId)
                }
            }
    }

    private fun setDestination(destination: Destination?) {
        _state.update { it.copy(destination = destination) }
    }

    private fun resetError() = _state.update { it.copy(appError = null) }

    private fun fetchData(movieId: Int) {
        val from = if (_state.value.contentKind == ContentKind.MOVIE) MOVIES else TV
        getDetails(from, movieId)
        getImages(from, movieId)
        getRecommendations(from, movieId)
        getSimilar(from, movieId)
        getCredits(from, movieId)
    }

    private fun getDetails(
        from: String,
        movieId: Int
    ) = run {
        getMovieDetails("$from$movieId").fold(
            ifLeft = { e -> _state.update { it.copy(appError = e.toAppError()) } },
            ifRight = { r -> _state.update { it.copy(movieDetail = r) } }
        )
    }

    private fun getCredits(
        from: String,
        movieId: Int
    ) = run {
        getMovieCastUseCase("$from$movieId").fold(
            ifLeft = { e -> _state.update { it.copy(appError = e.toAppError()) } },
            ifRight = { r -> _state.update { it.copy(movieCredits = r) } }
        )
    }

    private fun getImages(
        from: String,
        movieId: Int
    ) = run {
        delay(3000)
        getMovieImagesUseCase("$from$movieId").fold(
            ifLeft = { e -> _state.update { it.copy(appError = e.toAppError()) } },
            ifRight = { r ->
                _state.update { s -> s.copy(images = r.map { it.filePath }) }
            }
        )
    }

    private fun getRecommendations(
        from: String,
        movieId: Int
    ) = run {
        _state.update {
            it.copy(
                recommendations = getContent.asPagingFlow(
                    endpoint = "$from$movieId$RECOMMENDATIONS",
                    scope = viewModelScope
                )
            )
        }
    }

    private fun getSimilar(
        from: String,
        movieId: Int
    ) = run {
        _state.update {
            it.copy(
                similar = getContent.asPagingFlow(
                    endpoint = "$from$movieId$SIMILAR",
                    scope = viewModelScope
                )
            )
        }
    }

    private fun collectContentKind() {
        viewModelScope.launch {
            getContentKindUseCase()
                .distinctUntilChanged()
                .catch { e ->
                    _state.update { it.copy(appError = e.toAppError()) }
                }
                .collect { contentKind ->
                    _state.update { it.copy(contentKind = contentKind) }
                    getArgs(savedStateHandle)
                }
        }
    }

    private fun run(action: suspend CoroutineScope.() -> Unit) {
        viewModelScope.launch {
            try {
                _state.update { it.copy(isLoading = true) }
                action()
            } catch (e: Exception) {
                _state.update { it.copy(isLoading = false, appError = e.toAppError()) }
            } finally {
                _state.update { it.copy(isLoading = false) }
            }
        }
    }

    companion object {
        private const val RECOMMENDATIONS = "/recommendations"
        private const val SIMILAR = "/similar"
        private const val MOVIES = "movie/"
        private const val TV = "tv/"

    }

}


