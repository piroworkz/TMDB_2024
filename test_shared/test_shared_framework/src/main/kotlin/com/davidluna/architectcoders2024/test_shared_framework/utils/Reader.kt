package com.davidluna.architectcoders2024.test_shared_framework.utils

import kotlinx.serialization.json.Json
import java.io.InputStream

object Reader {

    val json = Json {
        ignoreUnknownKeys = true
        isLenient = true
    }

    inline fun <reified T> fromJson(fileName: String): T =
        this::class.java.getResourceAsStream("/raw/$fileName").use { stream: InputStream? ->
            requireNotNull(stream) { "File not found" }
            val jsonString = stream.bufferedReader().use { it.readText() }
            json.decodeFromString<T>(jsonString)
        }

}