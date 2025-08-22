package com.davidluna.tmdb.core_framework.data.remote.messaging

import android.Manifest.permission.POST_NOTIFICATIONS
import android.app.Application
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.checkSelfPermission
import com.davidluna.tmdb.core_framework.data.remote.model.NotificationChannelDetails
import com.davidluna.tmdb.core_framework.data.remote.model.NotificationDetails
import com.google.firebase.messaging.RemoteMessage
import javax.inject.Inject
import kotlin.random.Random

class TmdbNotificationsManager @Inject constructor(
    private val application: Application,
) : NotificationsManager {

    private val manager =
        ContextCompat.getSystemService(application, NotificationManager::class.java)
    private val compat = NotificationManagerCompat.from(application)
    private val isPermissionGranted: Boolean
        get() = Build.VERSION.SDK_INT >= 33 && checkSelfPermission(
            application,
            POST_NOTIFICATIONS
        ) == PackageManager.PERMISSION_GRANTED

    override fun showNotification(
        notification: NotificationDetails,
        channel: NotificationChannelDetails,
    ): Boolean {
        if (!isPermissionGranted) return false
        ensureChannel(channel)
        compat.notify(Random.nextInt(), notification.build(application))
        return true
    }

    override fun ensureChannel(channel: NotificationChannelDetails) {
        if (manager?.getNotificationChannel(channel.id) == null) {
            manager?.createNotificationChannel(
                NotificationChannel(channel.id, channel.name, channel.importance).apply {
                    description = channel.description
                }
            )
        }
    }

    private fun RemoteMessage.Notification.build(): Notification =
        NotificationCompat.Builder(application, channelId.orEmpty())
            .setContentTitle(title)
            .setContentText(body)
            .setAutoCancel(true)
            .build()

}