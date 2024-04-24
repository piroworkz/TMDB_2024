package com.davidluna.architectcoders2024.app.ui.screens.master

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.davidluna.architectcoders2024.app.data.remote.model.movies.RemoteMovie
import com.davidluna.architectcoders2024.app.data.repositories.MoviesRepository
import com.davidluna.architectcoders2024.domain.AppError
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MoviesViewModel(private val repository: MoviesRepository) : ViewModel() {

    private val _state = MutableStateFlow(State())
    val state = _state.asStateFlow()

    init {
        fetchMovies()
    }

    data class State(
        val isLoading: Boolean = false,
        val appError: AppError? = null,
        val movies: List<RemoteMovie> = emptyList(),
        val nowPlayingPage: Int = 0,
        val selectedMovieId: Int? = null
    )

    private fun fetchMovies() {
        getNowPlayingMovies()
        getPopularMovies()
        getTopRatedMovies()
        getUpcomingMovies()
    }

    fun sendEvent(event: MoviesEvent) {
        when (event) {
            is MoviesEvent.OnMovieClicked -> setSelectedMovieId(event.movieId)
            MoviesEvent.ResetError -> resetError()
        }
    }

    private fun resetError() {
        _state.update { it.copy(appError = null) }
    }

    private fun setSelectedMovieId(movieId: Int?) =
        _state.update { it.copy(selectedMovieId = movieId) }

    private fun getNowPlayingMovies(page: Int = 1) = run {
        repository.getNowPlayingMovies(page).fold(
            ifLeft = { e -> _state.update { it.copy(appError = e) } },
            ifRight = { r ->
                _state.update { s ->
                    s.copy(movies = s.movies.plus(r.results.filter { it.posterPath != null }))
                }
            }
        )
    }

    private fun getPopularMovies(page: Int = 1) = run {
        repository.getPopularMovies(page).fold(
            ifLeft = { e -> _state.update { it.copy(appError = e) } },
            ifRight = { r ->
                _state.update { s ->
                    s.copy(movies = s.movies.plus(r.results.filter { it.posterPath != null }))
                }
            }
        )
    }

    private fun getTopRatedMovies(page: Int = 1) = run {
        repository.getTopRatedMovies(page).fold(
            ifLeft = { e -> _state.update { it.copy(appError = e) } },
            ifRight = { r ->
                _state.update { s ->
                    s.copy(movies = s.movies.plus(r.results.filter { it.posterPath != null }))
                }
            }
        )
    }

    private fun getUpcomingMovies(page: Int = 1) = run {
        repository.getUpcomingMovies(page).fold(
            ifLeft = { e -> _state.update { it.copy(appError = e) } },
            ifRight = { r ->
                _state.update { s ->
                    s.copy(movies = s.movies.plus(r.results.filter { it.posterPath != null }))
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

sealed interface MoviesEvent {
    data class OnMovieClicked(val movieId: Int?) : MoviesEvent
    data object ResetError : MoviesEvent
}