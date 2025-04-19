package com.davidluna.tmdb.core_ui

import android.util.Log

fun String.log(name: String = javaClass.simpleName) {
    Log.d("<-- $name", this)
}
