package com.davidluna.architectcoders2024.app

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.MultiProcessDataStoreFactory
import com.davidluna.architectcoders2024.app.data.local.datastore.SessionDatastore
import com.davidluna.architectcoders2024.app.data.local.datastore.SessionSerializer
import com.davidluna.architectcoders2024.app.data.remote.Client
import com.davidluna.architectcoders2024.app.data.remote.MoviesInterceptor
import com.davidluna.protodatastore.Session
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

class App : Application() {
    lateinit var sessionDatastore: SessionDatastore
    lateinit var client: Client
    private lateinit var proto: DataStore<Session>

    override fun onCreate() {
        super.onCreate()
        initProto()
        sessionDatastore = SessionDatastore(proto)
        initApiClient()
    }

    private fun initApiClient() {
        client = Client(
            MoviesInterceptor(
                sessionDatastore,
                CoroutineScope(SupervisorJob() + Dispatchers.IO)
            )
        )
    }

    private fun initProto() {
        proto = MultiProcessDataStoreFactory.create(
            serializer = SessionSerializer,
            produceFile = { filesDir.resolve("session.preferences_pb") }
        )
    }
}

val Context.app: App
    get() = applicationContext as App