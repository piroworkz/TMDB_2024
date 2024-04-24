package com.davidluna.architectcoders2024.app.ui.screens.movies.master

sealed interface MoviesEvent {
    data class OnMovieClicked(val movieId: Int?) : MoviesEvent
    data object ResetError : MoviesEvent
}