package com.davidluna.tmdb.core_domain.usecases

import com.davidluna.tmdb.core_domain.entities.ChannelSpec

fun interface NotificationChannelSetProvider : () -> Map<String, ChannelSpec>