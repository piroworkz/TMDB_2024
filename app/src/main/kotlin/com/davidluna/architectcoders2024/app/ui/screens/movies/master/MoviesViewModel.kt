package com.davidluna.architectcoders2024.app.ui.screens.movies.master

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.davidluna.architectcoders2024.app.data.remote.model.movies.RemoteMovie
import com.davidluna.architectcoders2024.app.data.repositories.MoviesRepository
import com.davidluna.architectcoders2024.domain.AppError
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.emptyFlow
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
        val selectedMovieId: Int? = null,
        val popularMovies: Flow<PagingData<RemoteMovie>> = emptyFlow(),
        val topRatedMovies: Flow<PagingData<RemoteMovie>> = emptyFlow(),
        val upcomingMovies: Flow<PagingData<RemoteMovie>> = emptyFlow(),
        val nowPlayingMovies: Flow<PagingData<RemoteMovie>> = emptyFlow()
    )

    private fun fetchMovies() {
        getPopularMovies()
        getNowPlayingMovies()
        getTopRatedMovies()
        getUpcomingMovies()
    }

    private fun getPopularMovies() = run {
        _state.update { it.copy(popularMovies = repository.getMovies(POPULAR)) }
    }

    private fun getTopRatedMovies() = run {
        _state.update { it.copy(topRatedMovies = repository.getMovies(TOP_RATED)) }
    }

    private fun getUpcomingMovies() = run {
        _state.update { it.copy(upcomingMovies = repository.getMovies(UPCOMING)) }
    }

    private fun getNowPlayingMovies() = run {
        _state.update { it.copy(nowPlayingMovies = repository.getMovies(NOW_PLAYING)) }
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
        const val POPULAR = "popular"
        const val TOP_RATED = "top_rated"
        const val UPCOMING = "upcoming"
        const val NOW_PLAYING = "now_playing"
    }

}

