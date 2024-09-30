package com.davidluna.tmdb.media_domain.usecases

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class FormatDateUseCase () {
    operator fun invoke(releaseDate: String): String? = try {
        val originalFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val date = originalFormat.parse(releaseDate)
        val localizedFormat = SimpleDateFormat("dd MMMM, yyyy", Locale.getDefault())
        localizedFormat.format(date ?: Date())
    } catch (e: Exception) {
        null
    }
}

