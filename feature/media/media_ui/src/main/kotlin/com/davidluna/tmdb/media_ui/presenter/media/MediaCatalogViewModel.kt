package com.davidluna.tmdb.media_ui.presenter.media

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.davidluna.tmdb.core_domain.entities.ContentKind
import com.davidluna.tmdb.core_domain.entities.errors.AppError
import com.davidluna.tmdb.core_domain.entities.errors.toAppError
import com.davidluna.tmdb.core_domain.usecases.datastore.GetContentKindUseCase
import com.davidluna.tmdb.core_ui.navigation.destination.Destination
import com.davidluna.tmdb.media_domain.usecases.GetMediaCatalogUseCase
import com.davidluna.tmdb.media_ui.presenter.media.MediaEvent.OnMovieClicked
import com.davidluna.tmdb.media_ui.presenter.media.MediaEvent.ResetError
import com.davidluna.tmdb.media_ui.presenter.model.Catalog
import com.davidluna.tmdb.media_ui.presenter.paging.asPagingFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MediaCatalogViewModel @Inject constructor(
    private val getContent: GetMediaCatalogUseCase,
    private val getContentKind: GetContentKindUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(State())
    val state = _state
        .onStart { collectContentKind() }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000, 0),
            initialValue = State()
        )

    data class State(
        val isLoading: Boolean = false,
        val appError: AppError? = null,
        val destination: Destination? = null,
        val catalogs: List<Catalog> = emptyList()
    )

    fun sendEvent(event: MediaEvent) = when (event) {
        is OnMovieClicked -> setDestinationArgs(event.destination)
        ResetError -> resetError()
    }

    private fun resetError() =
        _state.update { it.copy(appError = null) }

    private fun setDestinationArgs(destination: Destination?) =
        _state.update { it.copy(destination = destination) }

    private fun collectContentKind() {
        viewModelScope.launch {
            getContentKind()
                .catch { e -> _state.update { it.copy(appError = e.toAppError()) } }
                .collect(::fetchContent)
        }
    }

    private fun fetchContent(contentKind: ContentKind) {
        val endpoints = if (contentKind == ContentKind.MOVIE) {
            listOf(
                POPULAR to POPULAR_TITLE,
                TOP_RATED to TOP_RATED_TITLE,
                UPCOMING to UPCOMING_TITLE,
                NOW_PLAYING to NOW_PLAYING_TITLE
            )
        } else {
            listOf(
                TV_POPULAR to POPULAR_TITLE,
                TV_TOP_RATED to TOP_RATED_TITLE,
                AIRING_TODAY to AIRING_TODAY_TITLE,
                ON_THE_AIR to ON_THE_AIR_TITLE
            )
        }
        val catalogs = endpoints.map {
            Catalog(
                catalogName = it.second,
                flow = getContent.asPagingFlow(it.first, viewModelScope)
            )
        }
        _state.update { it.copy(catalogs = catalogs) }
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
        private const val POPULAR_TITLE = "POPULAR"
        private const val TOP_RATED_TITLE = "TOP RATED"
        private const val UPCOMING_TITLE = "UPCOMING"
        private const val NOW_PLAYING_TITLE = "NOW PLAYING"
        private const val AIRING_TODAY_TITLE = "AIRING TODAY"
        private const val ON_THE_AIR_TITLE = "ON THE AIR"
    }

}
