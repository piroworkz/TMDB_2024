package com.davidluna.tmdb.core_framework.data.local.sources

import com.davidluna.tmdb.core_framework.data.local.model.Coordinates

fun interface LocationService : suspend () -> Coordinates?