package com.davidluna.architectcoders2024.app.data.local.datastore

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import com.davidluna.protodatastore.ProtoSession
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.SerializationException
import java.io.InputStream
import java.io.OutputStream

object ProtoSessionSerializer : Serializer<ProtoSession> {
    override val defaultValue: ProtoSession
        get() = default()

    override suspend fun readFrom(input: InputStream): ProtoSession {
        return try {
            ProtoSession.parseFrom(input)
        } catch (e: SerializationException) {
            throw CorruptionException("Cannot read proto.", e)
        }
    }

    override suspend fun writeTo(t: ProtoSession, output: OutputStream) {
        withContext(Dispatchers.IO) {
            t.writeTo(output)
        }
    }

    private fun default() =
        ProtoSession.getDefaultInstance()
}

