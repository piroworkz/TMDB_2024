package com.davidluna.architectcoders2024.app.ui.screens.movies.detail.views

import com.davidluna.architectcoders2024.app.ui.screens.movies.detail.MovieDetailViewModel

fun joinImages(state: MovieDetailViewModel.State): List<String> {
    return if (!state.movieDetail?.posterPath.isNullOrEmpty()) {
        listOf(state.movieDetail?.posterPath!!).plus(state.images)
    } else {
        state.images
    }
}