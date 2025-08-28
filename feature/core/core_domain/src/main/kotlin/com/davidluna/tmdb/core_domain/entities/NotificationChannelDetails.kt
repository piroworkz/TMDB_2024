package com.davidluna.tmdb.core_domain.entities

data class NotificationChannelDetails(
    val id: String,
    val name: String,
    val description: String,
    val importance: Int
)