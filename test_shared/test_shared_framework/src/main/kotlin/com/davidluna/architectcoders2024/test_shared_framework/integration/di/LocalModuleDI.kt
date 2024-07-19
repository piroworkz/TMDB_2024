package com.davidluna.architectcoders2024.test_shared_framework.integration.di

import androidx.datastore.core.DataStore
import com.davidluna.architectcoders2024.test_shared_framework.integration.local.FakeProtoDataStore
import com.davidluna.protodatastore.ProtoPreferences

class LocalModuleDI {
    val dataStore: DataStore<ProtoPreferences> by lazy {
        FakeProtoDataStore()
    }
}