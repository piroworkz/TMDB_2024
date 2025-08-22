package com.davidluna.tmdb.core_framework.data.remote.messaging

import com.davidluna.tmdb.core_framework.data.remote.model.NotificationChannelDetails
import com.davidluna.tmdb.core_framework.data.remote.model.NotificationDetails

interface NotificationsManager {
    fun showNotification(
        notification: NotificationDetails,
        channel: NotificationChannelDetails,
    ): Boolean

    fun ensureChannel(channel: NotificationChannelDetails)
}