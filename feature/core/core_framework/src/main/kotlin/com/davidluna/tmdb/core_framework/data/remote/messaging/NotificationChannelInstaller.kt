package com.davidluna.tmdb.core_framework.data.remote.messaging

import android.app.NotificationChannel
import android.app.NotificationManager
import com.davidluna.tmdb.core_domain.entities.ChannelSpec
import com.davidluna.tmdb.core_domain.usecases.NotificationChannelSetProvider
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NotificationChannelInstaller @Inject constructor(
    private val manager: NotificationManager,
    private val notificationChannelSetProvider: NotificationChannelSetProvider,
) : InstallNotificationChannels {

    override operator fun invoke() {
        val channels = notificationChannelSetProvider()
        manager.createNotificationChannels(channels.values.map { it.toNotificationChannel() })
    }

    private fun ChannelSpec.toNotificationChannel(): NotificationChannel =
        NotificationChannel(id, name, importance).apply {
            description = this@toNotificationChannel.description
        }
}