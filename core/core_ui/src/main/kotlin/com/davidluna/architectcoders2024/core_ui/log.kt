package com.davidluna.architectcoders2024.core_ui

import android.util.Log

fun String.log(name: String = javaClass.simpleName) {
    Log.d("<-- $name", "log: $this")
}