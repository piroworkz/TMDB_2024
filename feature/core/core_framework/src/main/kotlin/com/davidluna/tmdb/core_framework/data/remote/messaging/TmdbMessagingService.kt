package com.davidluna.tmdb.core_framework.data.remote.messaging

import android.app.NotificationManager
import com.davidluna.tmdb.core_domain.usecases.NotificationChannelSetProvider
import com.davidluna.tmdb.core_framework.data.remote.model.NotificationChannelDetails
import com.davidluna.tmdb.core_framework.data.remote.model.NotificationDetails
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class TmdbMessagingService : FirebaseMessagingService() {

    @Inject
    lateinit var showNotification: ShowNotification

    @Inject
    lateinit var notificationChannelSetProvider: NotificationChannelSetProvider

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        message.notification?.let { notification: RemoteMessage.Notification ->
            showNotification(
                notification.toNotificationDetails(),
                notification.channelId.toNotificationChannelDetails()
            )
        }
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        println("<-- Firebase Messaging Token --> $token")
    }

    private fun String?.toNotificationChannelDetails(): NotificationChannelDetails {
        val channel = notificationChannelSetProvider()[this]
        return NotificationChannelDetails(
            id = channel?.id.orEmpty(),
            name = channel?.name.orEmpty(),
            description = channel?.description.orEmpty(),
            importance = channel?.importance ?: NotificationManager.IMPORTANCE_DEFAULT,
        )
    }

    private fun RemoteMessage.Notification.toNotificationDetails(): NotificationDetails {
        return NotificationDetails(
            title = title.orEmpty(),
            message = body.orEmpty(),
            channelId = channelId.orEmpty()
        )
    }
}