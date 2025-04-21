package com.davidluna.tmdb.media_ui.view.utils

import com.davidluna.tmdb.media_domain.entities.Catalog
import com.davidluna.tmdb.media_domain.entities.MediaType

fun MediaType.bottomBarItems(): List<Catalog> =
    if (this == MediaType.MOVIE) {
        listOf(
            Catalog.MOVIE_NOW_PLAYING,
            Catalog.MOVIE_POPULAR,
            Catalog.MOVIE_TOP_RATED
        )
    } else {
        listOf(
            Catalog.TV_ON_THE_AIR,
            Catalog.TV_POPULAR,
            Catalog.TV_TOP_RATED
        )
    }