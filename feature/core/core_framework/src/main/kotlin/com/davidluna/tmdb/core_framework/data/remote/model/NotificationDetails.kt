package com.davidluna.tmdb.core_framework.data.remote.model

import android.app.Notification
import android.content.Context
import androidx.core.app.NotificationCompat

data class NotificationDetails(
    val title: String,
    val message: String,
    val channelId: String,
    val autocancel: Boolean,
    val iconId: Int,
) {
    fun build(context: Context): Notification =
        NotificationCompat.Builder(context, channelId)
            .setSmallIcon(iconId)
            .setContentTitle(title)
            .setContentText(message)
            .setAutoCancel(autocancel)
            .build()
}

