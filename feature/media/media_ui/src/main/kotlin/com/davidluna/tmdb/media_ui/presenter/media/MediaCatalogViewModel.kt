package com.davidluna.tmdb.media_ui.presenter.media

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.davidluna.tmdb.core_domain.entities.ContentKind
import com.davidluna.tmdb.core_domain.entities.errors.AppError
import com.davidluna.tmdb.core_domain.entities.errors.toAppError
import com.davidluna.tmdb.core_domain.usecases.datastore.GetContentKindUseCase
import com.davidluna.tmdb.core_ui.navigation.destination.Destination
import com.davidluna.tmdb.media_domain.entities.Media
import com.davidluna.tmdb.media_domain.usecases.GetMediaCatalogUseCase
import com.davidluna.tmdb.media_ui.presenter.paging.asPagingFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MediaCatalogViewModel @Inject constructor(
    private val getContent: GetMediaCatalogUseCase,
    private val getContentKind: GetContentKindUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(State())
    val state = _state.asStateFlow()

    init {
        collectContentKind()
    }

    data class State(
        val isLoading: Boolean = false,
        val appError: AppError? = null,
        val destination: Destination? = null,
        val contentKind: com.davidluna.tmdb.core_domain.entities.ContentKind = com.davidluna.tmdb.core_domain.entities.ContentKind.UNDEFINED,
        val firstList: Flow<PagingData<Media>> = emptyFlow(),
        val secondList: Flow<PagingData<Media>> = emptyFlow(),
        val thirdList: Flow<PagingData<Media>> = emptyFlow(),
        val fourthList: Flow<PagingData<Media>> = emptyFlow(),
    )

    fun sendEvent(event: MediaEvent) {
        when (event) {
            is MediaEvent.OnMovieClicked -> setDestinationArgs(event.destination)
            MediaEvent.ResetError -> resetError()
            is MediaEvent.OnUiReady -> fetchContent(event.contentKind)
        }
    }

    private fun fetchContent(contentKind: com.davidluna.tmdb.core_domain.entities.ContentKind) {
        if (contentKind == com.davidluna.tmdb.core_domain.entities.ContentKind.MOVIE) {
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

    private fun setDestinationArgs(destination: Destination?) =
        _state.update { it.copy(destination = destination) }

    private fun collectContentKind() {
        viewModelScope.launch {
            getContentKind()
                .catch { e ->
                    _state.update { it.copy(appError = e.toAppError()) }
                }
                .collect { contentKind ->
                    _state.update { it.copy(contentKind = contentKind) }
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
