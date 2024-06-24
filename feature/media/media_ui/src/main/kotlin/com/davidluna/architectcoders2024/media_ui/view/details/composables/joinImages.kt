package com.davidluna.architectcoders2024.media_ui.view.details.composables

import com.davidluna.architectcoders2024.media_ui.presenter.detail.MovieDetailViewModel


fun joinImages(state: MovieDetailViewModel.State): List<String> {
    return if (!state.movieDetail?.posterPath.isNullOrEmpty()) {
        listOf(state.movieDetail?.posterPath!!).plus(state.images)
    } else {
        state.images
    }
}