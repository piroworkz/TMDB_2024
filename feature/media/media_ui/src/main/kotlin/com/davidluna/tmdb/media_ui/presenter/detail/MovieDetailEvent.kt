package com.davidluna.tmdb.media_ui.presenter.detail

import com.davidluna.tmdb.core_ui.navigation.destination.Destination

sealed interface MovieDetailEvent {
    data class OnNavigate(val destination: Destination?) : MovieDetailEvent
    data class OnMovieSelected(val mediaId: Int, val appBarTitle: String) : MovieDetailEvent
    data object ResetError : MovieDetailEvent
}