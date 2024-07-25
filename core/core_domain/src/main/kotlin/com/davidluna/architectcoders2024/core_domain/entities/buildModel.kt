package com.davidluna.architectcoders2024.core_domain.entities

fun String.buildModel(width: String = "w185"): String = "https://image.tmdb.org/t/p/$width$this"
