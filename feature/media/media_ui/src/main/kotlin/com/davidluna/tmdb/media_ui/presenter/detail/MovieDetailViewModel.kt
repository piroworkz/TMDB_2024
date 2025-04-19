package com.davidluna.tmdb.media_ui.presenter.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.davidluna.tmdb.core_domain.entities.ContentKind
import com.davidluna.tmdb.core_domain.entities.ContentKind.MOVIE
import com.davidluna.tmdb.core_domain.entities.errors.AppError
import com.davidluna.tmdb.core_domain.entities.errors.toAppError
import com.davidluna.tmdb.core_domain.usecases.datastore.GetContentKindUseCase
import com.davidluna.tmdb.core_ui.di.MediaId
import com.davidluna.tmdb.core_ui.navigation.destination.Destination
import com.davidluna.tmdb.core_ui.navigation.destination.MediaNavigation.Detail
import com.davidluna.tmdb.media_domain.entities.Cast
import com.davidluna.tmdb.media_domain.entities.MediaDetails
import com.davidluna.tmdb.media_domain.usecases.GetMediaCastUseCase
import com.davidluna.tmdb.media_domain.usecases.GetMediaCatalogUseCase
import com.davidluna.tmdb.media_domain.usecases.GetMediaDetailsUseCase
import com.davidluna.tmdb.media_domain.usecases.GetMediaImagesUseCase
import com.davidluna.tmdb.media_ui.presenter.model.Catalog
import com.davidluna.tmdb.media_ui.presenter.paging.asPagingFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    @MediaId
    private val movieId: Int,
    private val getMovieDetails: GetMediaDetailsUseCase,
    private val getMediaImagesUseCase: GetMediaImagesUseCase,
    private val getMediaCastUseCase: GetMediaCastUseCase,
    private val getContent: GetMediaCatalogUseCase,
    private val getContentKindUseCase: GetContentKindUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(State())
    val state = _state
        .onStart { collectContentKind() }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = State()
        )

    data class State(
        val isLoading: Boolean = false,
        val appError: AppError? = null,
        val destination: Destination? = null,
        val mediaDetails: MediaDetails? = null,
        val movieCredits: List<Cast> = emptyList(),
        val images: List<String> = emptyList(),
        val catalogs: List<Catalog> = emptyList(),
    )

    fun sendEvent(event: MovieDetailEvent) {
        when (event) {
            is MovieDetailEvent.OnNavigate -> setDestination(event.destination)
            is MovieDetailEvent.OnMovieSelected -> onMovieSelected(event.mediaId, event.appBarTitle)
            MovieDetailEvent.ResetError -> resetError()
        }
    }

    private fun onMovieSelected(movieId: Int, appBarTitle: String) {
        _state.update { it.copy(destination = Detail(movieId, appBarTitle)) }
    }

    private fun setDestination(destination: Destination?) {
        _state.update { it.copy(destination = destination) }
    }

    private fun resetError() = _state.update { it.copy(appError = null) }

    private fun collectContentKind() {
        viewModelScope.launch {
            getContentKindUseCase()
                .catch { e -> _state.update { it.copy(appError = e.toAppError()) } }
                .collect { contentKind -> fetchData(contentKind) }
        }
    }

    private fun fetchData(contentKind: ContentKind) {
        run {
            val from = if (contentKind == MOVIE) MOVIES else TV
            getDetails(from, movieId)
            getImages(from, movieId)
            getCredits(from, movieId)
        }
        getCatalogs(contentKind)
    }

    private suspend fun getDetails(from: String, mediaId: Int) =
        getMovieDetails("$from$mediaId").fold(
            ifLeft = { e -> _state.update { it.copy(appError = e.toAppError()) } },
            ifRight = { r -> _state.update { it.copy(mediaDetails = r) } }
        )

    private suspend fun getCredits(from: String, movieId: Int) =
        getMediaCastUseCase("$from$movieId").fold(
            ifLeft = { e -> _state.update { it.copy(appError = e.toAppError()) } },
            ifRight = { r -> _state.update { it.copy(movieCredits = r) } }
        )

    private suspend fun getImages(from: String, movieId: Int) =
        getMediaImagesUseCase("$from$movieId").fold(
            ifLeft = { e -> _state.update { it.copy(appError = e.toAppError()) } },
            ifRight = { r -> _state.update { s -> s.copy(images = r.map { it.filePath }) } }
        )

    private fun getCatalogs(contentKind: ContentKind) {
        val mediaType = (if (contentKind == MOVIE) MOVIES else TV).plus(movieId)
        val endPoints = listOf(
            "$mediaType$RECOMMENDATIONS" to "RECOMMENDATIONS",
            "$mediaType$SIMILAR" to "SIMILAR"
        )
        val catalogs = endPoints.map { endPoint ->
            Catalog(
                catalogName = endPoint.second,
                flow = getContent.asPagingFlow(endPoint.first, viewModelScope)
            )
        }
        _state.update { it.copy(catalogs = catalogs) }
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


