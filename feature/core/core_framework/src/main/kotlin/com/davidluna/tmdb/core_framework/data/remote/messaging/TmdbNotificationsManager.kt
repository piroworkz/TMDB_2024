package com.davidluna.tmdb.core_framework.data.remote.messaging

import android.Manifest.permission.POST_NOTIFICATIONS
import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.checkSelfPermission
import com.davidluna.tmdb.core_framework.data.remote.model.NotificationChannelDetails
import com.davidluna.tmdb.core_framework.data.remote.model.NotificationDetails
import javax.inject.Inject
import kotlin.random.Random

class TmdbNotificationsManager @Inject constructor(
    private val application: Application,
) : ShowNotification {

    private val manager =
        ContextCompat.getSystemService(application, NotificationManager::class.java)
    private val compat = NotificationManagerCompat.from(application)
    private val isPermissionGranted: Boolean
        get() = Build.VERSION.SDK_INT >= 33 && checkSelfPermission(
            application,
            POST_NOTIFICATIONS
        ) == PackageManager.PERMISSION_GRANTED

    override operator fun invoke(
        notification: NotificationDetails,
        channel: NotificationChannelDetails,
    ): Boolean {
        println("<-- Show Notification --> $notification")
        if (!isPermissionGranted) return false
        ensureChannel(channel)
        compat.notify(Random.nextInt(), notification.build(application))
        return true
    }

    private fun ensureChannel(channel: NotificationChannelDetails) {
        if (manager?.getNotificationChannel(channel.id) == null) {
            manager?.createNotificationChannel(
                NotificationChannel(channel.id, channel.name, channel.importance).apply {
                    description = channel.description
                }
            )
        }
    }
}