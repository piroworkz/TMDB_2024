package com.davidluna.architectcoders2024.app.ui.screens.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.davidluna.architectcoders2024.app.data.remote.model.movies.RemoteImages
import com.davidluna.architectcoders2024.app.data.remote.model.movies.RemoteMovie
import com.davidluna.architectcoders2024.app.data.remote.model.movies.RemoteMovieCredits
import com.davidluna.architectcoders2024.app.data.remote.model.movies.RemoteMovieDetail
import com.davidluna.architectcoders2024.app.ui.navigation.destinations.Destination
import com.davidluna.architectcoders2024.app.ui.screens.master.views.buildModel
import com.davidluna.architectcoders2024.app.utils.toAppError
import com.davidluna.architectcoders2024.app.data.repositories.MovieDetailsRepository
import com.davidluna.architectcoders2024.domain.AppError
import com.davidluna.architectcoders2024.domain.toAppError
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MovieDetailViewModel(
    movieId: Int?,
    private val repository: MovieDetailsRepository
) : ViewModel() {

    private val _state = MutableStateFlow(State())
    val state = _state.asStateFlow()

    init {
        movieId?.let(::fetchData)
    }

    data class State(
        val isLoading: Boolean = false,
        val appError: AppError? = null,
        val destination: Destination? = null,
        val movieDetail: RemoteMovieDetail? = null,
        val movieCredits: RemoteMovieCredits? = null,
        val images: List<String> = emptyList(),
        val recommendations: List<RemoteMovie> = emptyList(),
        val similar: List<RemoteMovie> = emptyList(),
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
        repository.getMovieDetail(movieId).fold(
            ifLeft = { e -> _state.update { it.copy(appError = e.toAppError()) } },
            ifRight = { r -> _state.update { it.copy(movieDetail = r) } }
        )
    }

    private fun getMovieCredits(movieId: Int) = run {
        repository.getMovieCredits(movieId).fold(
            ifLeft = { e -> _state.update { it.copy(appError = e.toAppError()) } },
            ifRight = { r -> _state.update { it.copy(movieCredits = r) } }
        )
    }

    private fun getMovieImages(movieId: Int) = run {
        repository.getMovieImages(movieId).fold(
            ifLeft = { e -> _state.update { it.copy(appError = e.toAppError()) } },
            ifRight = { r -> r.toImageList() }
        )
    }

    private fun getMovieRecommendations(movieId: Int) = run {
        repository.getMovieRecommendations(movieId = movieId).fold(
            ifLeft = { e -> _state.update { it.copy(appError = e.toAppError()) } },
            ifRight = { r -> _state.update { s -> s.copy(recommendations = r.results.filter { it.posterPath != null }) } }
        )
    }

    private fun getSimilarMovies(movieId: Int) = run {
        repository.getSimilarMovies(movieId = movieId).fold(
            ifLeft = { e -> _state.update { it.copy(appError = e.toAppError()) } },
            ifRight = { r -> _state.update { s -> s.copy(similar = r.results.filter { it.posterPath != null }) } }
        )
    }

    private fun RemoteImages.toImageList() {
        val images: List<String> = posters.map { it.filePath.buildModel("w500") }
        _state.update { s -> s.copy(images = images) }
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

}
