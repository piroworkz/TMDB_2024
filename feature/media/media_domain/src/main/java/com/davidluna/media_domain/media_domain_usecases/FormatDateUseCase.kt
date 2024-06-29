package com.davidluna.media_domain.media_domain_usecases

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

class FormatDateUseCase @Inject constructor() {
    operator fun invoke(releaseDate: String): String {
        val originalFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val date = originalFormat.parse(releaseDate)
        val localizedFormat = SimpleDateFormat("dd MMMM, yyyy", Locale.getDefault())
        return localizedFormat.format(date ?: Date())
    }
}