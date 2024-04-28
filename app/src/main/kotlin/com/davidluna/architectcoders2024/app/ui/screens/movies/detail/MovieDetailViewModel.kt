package com.davidluna.architectcoders2024.app.ui.screens.movies.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.davidluna.architectcoders2024.app.data.toAppError
import com.davidluna.architectcoders2024.app.ui.navigation.destinations.Destination
import com.davidluna.architectcoders2024.app.ui.navigation.safe_args.Args
import com.davidluna.architectcoders2024.app.utils.asPagingFlow
import com.davidluna.architectcoders2024.domain.responses.Cast
import com.davidluna.architectcoders2024.domain.responses.movies.Movie
import com.davidluna.architectcoders2024.domain.responses.movies.MovieDetail
import com.davidluna.architectcoders2024.usecases.movies.GetMovieCastUseCase
import com.davidluna.architectcoders2024.usecases.movies.GetMovieDetailsUseCase
import com.davidluna.architectcoders2024.usecases.movies.GetMovieImagesUseCase
import com.davidluna.architectcoders2024.usecases.movies.GetMoviesUseCase
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
class MovieDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getMovieDetails: GetMovieDetailsUseCase,
    private val getMovieImagesUseCase: GetMovieImagesUseCase,
    private val getMovieCastUseCase: GetMovieCastUseCase,
    private val getMoviesUseCase: GetMoviesUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(State())
    val state = _state.asStateFlow()

    init {
        savedStateHandle.get<Int>(Args.MovieId.name)?.let(::fetchData)
    }

    data class State(
        val isLoading: Boolean = false,
        val appError: com.davidluna.architectcoders2024.domain.AppError? = null,
        val destination: Destination? = null,
        val movieDetail: MovieDetail? = null,
        val movieCredits: List<Cast> = emptyList(),
        val images: List<String> = emptyList(),
        val recommendations: Flow<PagingData<Movie>> = emptyFlow(),
        val similar: Flow<PagingData<Movie>> = emptyFlow(),
    )

    fun sendEvent(event: MovieDetailEvent) {
        when (event) {
            is MovieDetailEvent.OnNavigate -> setDestination(event.destination)
            MovieDetailEvent.ResetError -> resetError()
        }
    }

    private fun setDestination(destination: Destination?) {
        _state.update { it.copy(destination = destination) }
    }

    private fun resetError() = _state.update { it.copy(appError = null) }

    private fun fetchData(movieId: Int) {
        getMovieDetail(movieId)
        getMovieImages(movieId)
        getMovieRecommendations(movieId)
        getSimilarMovies(movieId)
        getMovieCredits(movieId)
    }

    private fun getMovieDetail(movieId: Int) = run {
        getMovieDetails(movieId).fold(
            ifLeft = { e -> _state.update { it.copy(appError = e.toAppError()) } },
            ifRight = { r -> _state.update { it.copy(movieDetail = r) } }
        )
    }

    private fun getMovieCredits(movieId: Int) = run {
        getMovieCastUseCase(movieId).fold(
            ifLeft = { e -> _state.update { it.copy(appError = e.toAppError()) } },
            ifRight = { r -> _state.update { it.copy(movieCredits = r) } }
        )
    }

    private fun getMovieImages(movieId: Int) = run {
        getMovieImagesUseCase(movieId).fold(
            ifLeft = { e -> _state.update { it.copy(appError = e.toAppError()) } },
            ifRight = { r -> _state.update { s -> s.copy(images = r.map { it.filePath }) } }
        )
    }

    private fun getMovieRecommendations(movieId: Int) = run {
        _state.update { it.copy(recommendations = getMoviesUseCase.asPagingFlow("$movieId$RECOMMENDATIONS")) }
    }

    private fun getSimilarMovies(movieId: Int) = run {
        _state.update { it.copy(similar = getMoviesUseCase.asPagingFlow("$movieId$SIMILAR")) }
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
    }

}
