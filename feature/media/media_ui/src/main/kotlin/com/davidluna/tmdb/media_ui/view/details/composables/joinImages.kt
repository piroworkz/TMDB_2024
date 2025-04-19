package com.davidluna.tmdb.media_ui.view.details.composables

import com.davidluna.tmdb.media_ui.presenter.detail.MovieDetailViewModel


fun joinImages(state: MovieDetailViewModel.State): List<String> {
    return if (!state.mediaDetails?.posterPath.isNullOrEmpty()) {
        listOf(state.mediaDetails?.posterPath!!).plus(state.images)
    } else {
        state.images
    }
}