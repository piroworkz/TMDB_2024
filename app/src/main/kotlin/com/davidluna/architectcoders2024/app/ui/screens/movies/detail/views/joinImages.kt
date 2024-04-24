package com.davidluna.architectcoders2024.app.ui.screens.movies.detail.views

import com.davidluna.architectcoders2024.app.ui.screens.movies.detail.MovieDetailViewModel
import com.davidluna.architectcoders2024.app.ui.screens.movies.master.views.buildModel

fun joinImages(state: MovieDetailViewModel.State): List<String> {
    return if (!state.movieDetail?.posterPath.isNullOrEmpty()) {
        listOf(state.movieDetail?.posterPath!!.buildModel("w500")).plus(state.images)
    } else {
        state.images
    }
}