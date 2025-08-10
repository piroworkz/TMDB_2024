package com.davidluna.tmdb.media_ui.view.utils

import com.davidluna.tmdb.media_domain.entities.Catalog
import com.davidluna.tmdb.core_ui.R

val Catalog.title: Int?
    get() = when (this) {
        Catalog.MOVIE_NOW_PLAYING -> R.string.now_playing_title
        Catalog.MOVIE_POPULAR, Catalog.TV_POPULAR -> R.string.catalog_popular_title
        Catalog.MOVIE_RECOMMENDATIONS, Catalog.TV_RECOMMENDATIONS -> R.string.catalog_recommendations_title
        Catalog.MOVIE_SIMILAR, Catalog.TV_SIMILAR -> R.string.catalog_similar_title
        Catalog.MOVIE_UPCOMING -> R.string.catalog_upcoming_title
        Catalog.TV_AIRING_TODAY -> R.string.catalog_airing_today_title
        Catalog.TV_ON_THE_AIR -> R.string.catalog_on_the_air_title
        Catalog.TV_TOP_RATED, Catalog.MOVIE_TOP_RATED -> R.string.catalog_top_rated_title
        else -> null
    }