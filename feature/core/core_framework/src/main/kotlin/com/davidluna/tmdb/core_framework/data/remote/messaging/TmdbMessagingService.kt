package com.davidluna.tmdb.core_framework.data.remote.messaging

import android.annotation.SuppressLint
import com.google.firebase.messaging.FirebaseMessagingService
import dagger.hilt.android.AndroidEntryPoint

@SuppressLint("MissingFirebaseInstanceTokenRefresh")
@AndroidEntryPoint
class TmdbMessagingService : FirebaseMessagingService()