package com.davidluna.architectcoders2024.app.ui.screens.movies.master

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.davidluna.architectcoders2024.app.ui.navigation.destinations.Destination
import com.davidluna.architectcoders2024.app.utils.asPagingFlow
import com.davidluna.architectcoders2024.domain.AppError
import com.davidluna.architectcoders2024.domain.responses.movies.Movie
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
class MoviesViewModel @Inject constructor(
    private val getMovies: GetMoviesUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(State())
    val state = _state.asStateFlow()

    init {
        fetchMovies()
    }

    data class State(
        val isLoading: Boolean = false,
        val appError: AppError? = null,
        val destination: Destination? = null,
        val popularMovies: Flow<PagingData<Movie>> = emptyFlow(),
        val topRatedMovies: Flow<PagingData<Movie>> = emptyFlow(),
        val upcomingMovies: Flow<PagingData<Movie>> = emptyFlow(),
        val nowPlayingMovies: Flow<PagingData<Movie>> = emptyFlow()
    )

    private fun fetchMovies() {
        getPopularMovies()
        getNowPlayingMovies()
        getTopRatedMovies()
        getUpcomingMovies()
    }

    fun sendEvent(event: MoviesEvent) {
        when (event) {
            is MoviesEvent.OnMovieClicked -> setDestinationArgs(event.destination)
            MoviesEvent.ResetError -> resetError()
        }
    }

    private fun getPopularMovies() {
        _state.update { it.copy(popularMovies = getMovies.asPagingFlow(POPULAR)) }
    }

    private fun getTopRatedMovies() = run {
        _state.update { it.copy(topRatedMovies = getMovies.asPagingFlow(TOP_RATED)) }
    }

    private fun getUpcomingMovies() = run {
        _state.update { it.copy(upcomingMovies = getMovies.asPagingFlow(UPCOMING)) }
    }

    private fun getNowPlayingMovies() = run {
        _state.update { it.copy(nowPlayingMovies = getMovies.asPagingFlow(NOW_PLAYING)) }
    }

    private fun resetError() {
        _state.update { it.copy(appError = null) }
    }

    private fun setDestinationArgs(destination: Destination?) =
        _state.update { it.copy(destination = destination) }


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
