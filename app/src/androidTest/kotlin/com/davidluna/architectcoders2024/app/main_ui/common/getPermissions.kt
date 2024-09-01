package com.davidluna.architectcoders2024.app.main_ui.common

import android.Manifest

fun getPermissions() =
    arrayOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION,
    )