package com.davidluna.tmdb.core_domain.entities

data class NotificationDetails(
    val title: String,
    val message: String,
    val channelId: String,
    val autocancel: Boolean = true,
)