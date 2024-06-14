package com.davidluna.architectcoders2024.app.ui.screens.content.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.davidluna.architectcoders2024.app.data.toAppError
import com.davidluna.architectcoders2024.app.ui.navigation.destinations.Destination
import com.davidluna.architectcoders2024.app.ui.navigation.safe_args.Args
import com.davidluna.architectcoders2024.app.ui.navigation.safe_args.ContentType
import com.davidluna.architectcoders2024.app.ui.navigation.safe_args.ContentType.MOVIE
import com.davidluna.architectcoders2024.app.utils.asPagingFlow
import com.davidluna.architectcoders2024.domain.responses.Cast
import com.davidluna.architectcoders2024.domain.responses.movies.Content
import com.davidluna.architectcoders2024.domain.responses.movies.Details
import com.davidluna.architectcoders2024.usecases.movies.GetContentUseCase
import com.davidluna.architectcoders2024.usecases.movies.GetMovieCastUseCase
import com.davidluna.architectcoders2024.usecases.movies.GetMovieDetailsUseCase
import com.davidluna.architectcoders2024.usecases.movies.GetMovieImagesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ItemDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getMovieDetails: GetMovieDetailsUseCase,
    private val getMovieImagesUseCase: GetMovieImagesUseCase,
    private val getMovieCastUseCase: GetMovieCastUseCase,
    private val getContent: GetContentUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(State())
    val state = _state.asStateFlow()

    init {
        savedStateHandle.apply {
            get<ContentType>(Args.Type.name)?.let { contentType ->
                _state.update { s -> s.copy(contentType = contentType) }
                get<Int>(Args.MovieId.name)?.let { fetchData(it, contentType) }
            }
        }
    }

    data class State(
        val isLoading: Boolean = false,
        val appError: com.davidluna.architectcoders2024.domain.AppError? = null,
        val contentType: ContentType? = null,
        val destination: Destination? = null,
        val movieDetail: Details? = null,
        val movieCredits: List<Cast> = emptyList(),
        val images: List<String> = emptyList(),
        val recommendations: Flow<PagingData<Content>> = emptyFlow(),
        val similar: Flow<PagingData<Content>> = emptyFlow(),
    )

    fun sendEvent(event: ItemDetailEvent) {
        when (event) {
            is ItemDetailEvent.OnNavigate -> setDestination(event.destination)
            ItemDetailEvent.ResetError -> resetError()
        }
    }

    private fun setDestination(destination: Destination?) {
        _state.update { it.copy(destination = destination) }
    }

    private fun resetError() = _state.update { it.copy(appError = null) }

    private fun fetchData(movieId: Int, contentType: ContentType) {
        val from = if (contentType == MOVIE) MOVIES else TV
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
        getMovieImagesUseCase("$from$movieId").fold(
            ifLeft = { e -> _state.update { it.copy(appError = e.toAppError()) } },
            ifRight = { r -> _state.update { s -> s.copy(images = r.map { it.filePath }) } }
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
