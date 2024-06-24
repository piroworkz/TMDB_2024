package com.davidluna.architectcoders2024.media_ui.presenter.media

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.davidluna.architectcoders2024.core_domain.core_entities.AppError
import com.davidluna.architectcoders2024.core_domain.core_entities.ContentKind
import com.davidluna.architectcoders2024.core_domain.core_entities.toAppError
import com.davidluna.architectcoders2024.core_domain.core_usecases.datastore.GetContentKindUseCase
import com.davidluna.architectcoders2024.media_ui.presenter.paging.asPagingFlow
import com.davidluna.media_domain.media_domain_entities.Media
import com.davidluna.media_domain.media_domain_usecases.GetContentUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
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
class MoviesViewModel @Inject constructor(
    private val getContent: GetContentUseCase,
    private val getContentKind: GetContentKindUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(State())
    val state = _state.asStateFlow()

    data class State(
        val isLoading: Boolean = false,
        val appError: AppError? = null,
        val destination: com.davidluna.architectcoders2024.navigation.model.Destination? = null,
        val contentKind: ContentKind = ContentKind.MOVIE,
        val firstList: Flow<PagingData<Media>> = emptyFlow(),
        val secondList: Flow<PagingData<Media>> = emptyFlow(),
        val thirdList: Flow<PagingData<Media>> = emptyFlow(),
        val fourthList: Flow<PagingData<Media>> = emptyFlow()
    )

    fun sendEvent(event: MoviesEvent) {
        when (event) {
            is MoviesEvent.OnMovieClicked -> setDestinationArgs(event.destination)
            MoviesEvent.ResetError -> resetError()
            MoviesEvent.OnViewReady -> collectContentKind()
        }
    }

    private fun fetchContent() {
        if (_state.value.contentKind == ContentKind.MOVIE) {
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
        _state.update {
            it.copy(
                secondList = getContent.asPagingFlow(
                    TV_TOP_RATED,
                    viewModelScope
                )
            )
        }

    private fun getAiringToday() =
        _state.update { it.copy(thirdList = getContent.asPagingFlow(AIRING_TODAY, viewModelScope)) }

    private fun getOnAir() =
        _state.update { it.copy(fourthList = getContent.asPagingFlow(ON_THE_AIR, viewModelScope)) }

    private fun resetError() =
        _state.update { it.copy(appError = null) }

    private fun setDestinationArgs(destination: com.davidluna.architectcoders2024.navigation.model.Destination?) =
        _state.update { it.copy(destination = destination) }

    private fun collectContentKind() {
        viewModelScope.launch {
            getContentKind()
                .distinctUntilChanged()
                .catch { e ->
                    _state.update { it.copy(appError = e.toAppError()) }
                }
                .collect { contentKind ->
                    _state.update { it.copy(contentKind = contentKind) }
                    fetchContent()
                }
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
