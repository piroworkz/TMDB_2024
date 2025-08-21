package com.davidluna.tmdb.core_framework.data.remote.messaging

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class TmdbMessagingService() : FirebaseMessagingService() {

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        println("<-- Firebase Messaging Message --> $message")
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        println("<-- Firebase Messaging Token --> $token")
    }
}