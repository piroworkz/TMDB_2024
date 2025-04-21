package com.davidluna.tmdb.core_ui.utils

import android.util.Log

@Suppress("unused")
fun String.log(name: String = javaClass.simpleName) {
    Log.d("<-- $name", this)
}

fun Boolean.toggle(): Boolean = !this