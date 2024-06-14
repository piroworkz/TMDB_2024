package com.davidluna.architectcoders2024.app.ui.screens.content.detail.views

import com.davidluna.architectcoders2024.app.ui.screens.content.detail.ItemDetailViewModel

fun joinImages(state: ItemDetailViewModel.State): List<String> {
    return if (!state.movieDetail?.posterPath.isNullOrEmpty()) {
        listOf(state.movieDetail?.posterPath!!).plus(state.images)
    } else {
        state.images
    }
}