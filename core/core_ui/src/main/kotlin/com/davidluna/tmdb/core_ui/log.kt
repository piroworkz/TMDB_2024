package com.davidluna.tmdb.core_ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn

fun String.log(name: String = javaClass.simpleName) {
    Log.d("<-- $name", this)
}

context(ViewModel)
fun <T> MutableStateFlow<T>.onInit(
    initializeState: () -> Unit
): StateFlow<T> =
    onStart { initializeState() }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = value
        )
