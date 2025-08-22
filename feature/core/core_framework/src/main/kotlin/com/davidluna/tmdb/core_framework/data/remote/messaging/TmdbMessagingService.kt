package com.davidluna.tmdb.core_framework.data.remote.messaging

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class TmdbMessagingService : FirebaseMessagingService() {

    @Inject
    lateinit var notificationsManager: NotificationsManager

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        message.notification?.let {
            println("<-- Firebase Messaging Message --> $it")
        }
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        println("<-- Firebase Messaging Token --> $token")
    }
}