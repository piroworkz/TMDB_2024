package com.davidluna.tmdb.test_shared.reader

import kotlinx.serialization.json.Json
import java.io.InputStream

object Reader {

    val json: Json = Json {
        ignoreUnknownKeys = true
        isLenient = true
    }

    inline fun <reified T> fromJson(fileName: String): T =
        this::class.java.getResourceAsStream("/raw/$fileName").use { stream: InputStream? ->
            requireNotNull(stream) { "File not found" }
            val jsonString = stream.bufferedReader().use { it.readText() }
            json.decodeFromString<T>(jsonString)
        }

    fun fromFile(fileName: String): String =
        this::class.java.getResourceAsStream("/raw/$fileName").use { stream: InputStream? ->
            requireNotNull(stream) { "File not found" }
            stream.bufferedReader().use { it.readText() }
        }

}