package com.davidluna.tmdb.core_framework.data.local.sources

import com.davidluna.tmdb.core_framework.data.local.model.Coordinates

fun interface CountryCodeResolver : (Coordinates) -> String {
    companion object {
        const val DEFAULT_COUNTRY_CODE = "MX"
    }
}