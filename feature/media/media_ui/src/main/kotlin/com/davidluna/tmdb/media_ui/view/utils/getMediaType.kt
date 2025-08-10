package com.davidluna.tmdb.media_ui.view.utils

import com.davidluna.tmdb.media_domain.entities.Catalog
import com.davidluna.tmdb.media_domain.entities.MediaType

fun Catalog.getMediaType(): MediaType = if (this.name.contains("TV_")) {
    MediaType.TV_SHOW
} else {
    MediaType.MOVIE
}