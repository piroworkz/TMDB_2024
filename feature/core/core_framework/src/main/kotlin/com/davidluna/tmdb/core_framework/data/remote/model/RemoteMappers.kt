package com.davidluna.tmdb.core_framework.data.remote.model

import com.davidluna.tmdb.core_domain.entities.AppError
import com.davidluna.tmdb.core_domain.entities.AppErrorCode
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import java.util.Locale

fun String.buildModel(width: String = "w185"): String =
    "https://image.tmdb.org/t/p/$width$this"

fun RemoteError.toAppError(): AppError = AppError(
    code = AppErrorCode.SERVER,
    description = statusMessage,
    type = null
)

fun formatDate(releaseDate: String?): String? = try {
    releaseDate?.let {
        val inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.US)
        val date = LocalDate.parse(it, inputFormatter)
        "${date.month.name.lowercase()} ${date.dayOfMonth}, ${date.year}"
    }
} catch (_: DateTimeParseException) {
    null
}