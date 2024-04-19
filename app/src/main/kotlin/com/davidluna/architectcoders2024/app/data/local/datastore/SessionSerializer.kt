package com.davidluna.architectcoders2024.app.data.local.datastore

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import com.davidluna.protodatastore.Session
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.SerializationException
import java.io.InputStream
import java.io.OutputStream

object SessionSerializer : Serializer<Session> {
    override val defaultValue: Session
        get() = default()

    override suspend fun readFrom(input: InputStream): Session {
        return try {
            Session.parseFrom(input)
        } catch (e: SerializationException) {
            throw CorruptionException("Cannot read proto.", e)
        }
    }

    override suspend fun writeTo(t: Session, output: OutputStream) {
        withContext(Dispatchers.IO) {
            t.writeTo(output)
        }
    }

    private fun default() =
        Session.getDefaultInstance()
}

