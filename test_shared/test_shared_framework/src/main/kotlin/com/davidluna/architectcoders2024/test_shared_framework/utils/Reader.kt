package com.davidluna.architectcoders2024.test_shared_framework.utils

import com.google.gson.Gson
import java.io.InputStream
import java.io.InputStreamReader

object Reader {

    inline fun <reified T> fromJson(fileName: String): T =
        this::class.java.getResourceAsStream("/raw/$fileName").use { stream: InputStream? ->
            requireNotNull(stream) { "File not found" }
            InputStreamReader(stream).use { reader: InputStreamReader ->
                Gson().fromJson(reader, T::class.java)
            }
        }

}