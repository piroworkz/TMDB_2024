package com.davidluna.architectcoders2024.app.ui.screens.content.master

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.davidluna.architectcoders2024.app.ui.navigation.destinations.Destination
import com.davidluna.architectcoders2024.app.ui.navigation.safe_args.Args
import com.davidluna.architectcoders2024.app.ui.navigation.safe_args.ContentType
import com.davidluna.architectcoders2024.app.utils.asPagingFlow
import com.davidluna.architectcoders2024.domain.AppError
import com.davidluna.architectcoders2024.domain.responses.movies.Content
import com.davidluna.architectcoders2024.usecases.movies.GetContentUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class MainContentViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getContent: GetContentUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(State())
    val state = _state.asStateFlow()

    init {
        getArgs(savedStateHandle)
    }

    data class State(
        val isLoading: Boolean = false,
        val appError: AppError? = null,
        val destination: Destination? = null,
        val contentType: ContentType? = null,
        val firstList: Flow<PagingData<Content>> = emptyFlow(),
        val secondList: Flow<PagingData<Content>> = emptyFlow(),
        val thirdList: Flow<PagingData<Content>> = emptyFlow(),
        val fourthList: Flow<PagingData<Content>> = emptyFlow()
    )

    fun sendEvent(event: MainContentEvent) {
        when (event) {
            is MainContentEvent.OnMovieClicked -> setDestinationArgs(event.destination)
            MainContentEvent.ResetError -> resetError()
        }
    }

    private fun fetchContent(contentType: ContentType) {
        if (contentType == ContentType.MOVIE) {
            getPopularMovies()
            getTopRatedMovies()
            getNowPlayingMovies()
            getUpcomingMovies()
        } else {
            getAiringToday()
            getOnAir()
            getTvPopular()
            getTvTopRated()
        }
    }

    private fun getPopularMovies() =
        _state.update { it.copy(firstList = getContent.asPagingFlow(POPULAR, viewModelScope)) }

    private fun getTopRatedMovies() =
        _state.update { it.copy(secondList = getContent.asPagingFlow(TOP_RATED, viewModelScope)) }

    private fun getUpcomingMovies() =
        _state.update { it.copy(thirdList = getContent.asPagingFlow(UPCOMING, viewModelScope)) }

    private fun getNowPlayingMovies() =
        _state.update { it.copy(fourthList = getContent.asPagingFlow(NOW_PLAYING, viewModelScope)) }

    private fun getTvPopular() =
        _state.update { it.copy(firstList = getContent.asPagingFlow(TV_POPULAR, viewModelScope)) }

    private fun getTvTopRated() =
        _state.update { it.copy(secondList = getContent.asPagingFlow(TV_TOP_RATED, viewModelScope)) }

    private fun getAiringToday() =
        _state.update { it.copy(thirdList = getContent.asPagingFlow(AIRING_TODAY, viewModelScope)) }

    private fun getOnAir() =
        _state.update { it.copy(fourthList = getContent.asPagingFlow(ON_THE_AIR, viewModelScope)) }

    private fun resetError() =
        _state.update { it.copy(appError = null) }

    private fun setDestinationArgs(destination: Destination?) =
        _state.update { it.copy(destination = destination) }

    private fun getArgs(savedStateHandle: SavedStateHandle) {
        savedStateHandle.get<ContentType>(Args.Type.name)?.let { contentType ->
            _state.update { it.copy(contentType = contentType) }
            fetchContent(contentType)
        }
    }

    companion object {
        private const val POPULAR = "movie/popular"
        private const val TOP_RATED = "movie/top_rated"
        private const val UPCOMING = "movie/upcoming"
        private const val NOW_PLAYING = "movie/now_playing"
        private const val AIRING_TODAY = "tv/airing_today"
        private const val ON_THE_AIR = "tv/on_the_air"
        private const val TV_POPULAR = "tv/popular"
        private const val TV_TOP_RATED = "tv/top_rated"

    }

}
