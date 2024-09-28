package com.davidluna.tmdb.auth_domain.usecases

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone
import javax.inject.Inject

class GuestSessionNotExpiredUseCase @Inject constructor() {
    operator fun invoke(expirationDate: String?): Boolean = try {
        requireNotNull(expirationDate)
        val expiresAt = SimpleDateFormat("yyyy-MM-dd HH:mm:ss z", Locale.getDefault())
            .apply { timeZone = TimeZone.getTimeZone("UTC") }
            .parse(expirationDate) ?: Date()
        Date().before(expiresAt)
    } catch (e: Exception) {
        false
    }
}