package com.davidluna.tmdb.core_domain.usecases

import com.davidluna.tmdb.core_domain.entities.NotificationChannelDetails
import com.davidluna.tmdb.core_domain.entities.NotificationDetails

fun interface ShowNotification : (NotificationDetails, NotificationChannelDetails) -> Boolean