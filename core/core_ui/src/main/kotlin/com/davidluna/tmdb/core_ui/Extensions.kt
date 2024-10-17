package com.davidluna.tmdb.core_ui

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn

fun String.log(name: String = javaClass.simpleName) {
    Log.d("<-- $name", this)
}

fun <T> MutableStateFlow<T>.onInit(
    scope: CoroutineScope,
    initializeState: () -> Unit
): StateFlow<T> =
    onStart { initializeState() }
        .stateIn(
            scope = scope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = value
        )
