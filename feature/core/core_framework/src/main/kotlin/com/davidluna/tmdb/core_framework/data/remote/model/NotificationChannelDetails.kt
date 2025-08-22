package com.davidluna.tmdb.core_framework.data.remote.model

import android.app.NotificationManager

data class NotificationChannelDetails(
    val id: String,
    val name: String,
    val description: String,
    val importance: Int = NotificationManager.IMPORTANCE_DEFAULT
)