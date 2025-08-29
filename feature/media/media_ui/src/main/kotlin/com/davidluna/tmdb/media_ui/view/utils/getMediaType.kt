package com.davidluna.tmdb.media_ui.view.utils

import com.davidluna.tmdb.media_domain.entities.Catalog
import com.davidluna.tmdb.media_domain.entities.MediaType

val Catalog.mediaType: MediaType
    get() = if (this.name.contains("TV_")) {
        MediaType.TV_SHOW
    } else {
        MediaType.MOVIE
    }