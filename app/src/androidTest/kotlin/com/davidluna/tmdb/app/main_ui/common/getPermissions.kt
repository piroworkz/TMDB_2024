package com.davidluna.tmdb.app.main_ui.common

import android.Manifest

internal val permissions
    get() = arrayOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION,
    )