package com.davidluna.tmdb.core_framework.data.remote.messaging

import com.davidluna.tmdb.core_framework.data.remote.model.NotificationChannelDetails
import com.davidluna.tmdb.core_framework.data.remote.model.NotificationDetails

fun interface ShowNotification : (NotificationDetails, NotificationChannelDetails) -> Boolean