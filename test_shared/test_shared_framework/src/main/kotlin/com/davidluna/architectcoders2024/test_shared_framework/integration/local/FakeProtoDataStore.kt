package com.davidluna.architectcoders2024.test_shared_framework.integration.local

import androidx.datastore.core.DataStore
import com.davidluna.protodatastore.ProtoPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakeProtoDataStore : DataStore<ProtoPreferences> {

    private var fakePreferences: ProtoPreferences =
        ProtoPreferences.getDefaultInstance()

    override val data: Flow<ProtoPreferences>
        get() = flowOf(fakePreferences)

    override suspend fun updateData(transform: suspend (t: ProtoPreferences) -> ProtoPreferences): ProtoPreferences {
        fakePreferences = transform(fakePreferences)
        return fakePreferences
    }
}
