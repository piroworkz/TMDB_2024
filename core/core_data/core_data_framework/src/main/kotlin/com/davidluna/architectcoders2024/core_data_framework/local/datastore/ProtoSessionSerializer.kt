package com.davidluna.architectcoders2024.core_data_framework.local.datastore

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import com.davidluna.protodatastore.ProtoPreferences
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.SerializationException
import java.io.InputStream
import java.io.OutputStream

object ProtoPreferencesSerializer : Serializer<ProtoPreferences> {

    override val defaultValue: ProtoPreferences
        get() = ProtoPreferences.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): ProtoPreferences {
        return try {
            ProtoPreferences.parseFrom(input)
        } catch (e: SerializationException) {
            throw CorruptionException("Cannot read proto.", e)
        }
    }

    override suspend fun writeTo(t: ProtoPreferences, output: OutputStream) {
        withContext(Dispatchers.IO) {
            t.writeTo(output)
        }
    }

}

