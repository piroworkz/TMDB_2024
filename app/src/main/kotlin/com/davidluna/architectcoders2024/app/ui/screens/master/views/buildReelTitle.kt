package com.davidluna.architectcoders2024.app.ui.screens.master.views

import androidx.compose.runtime.Composable
import com.davidluna.architectcoders2024.app.data.remote.model.movies.RemoteMovie

@Composable
fun buildReelTitle(movies: List<RemoteMovie>) =
    movies.first().type.name.replace("_", " ")