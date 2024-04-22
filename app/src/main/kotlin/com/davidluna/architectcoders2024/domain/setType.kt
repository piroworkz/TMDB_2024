package com.davidluna.architectcoders2024.domain

import com.davidluna.architectcoders2024.app.data.remote.model.movies.RemoteMovie
import com.davidluna.architectcoders2024.app.data.remote.model.movies.RemoteResults
import com.davidluna.architectcoders2024.domain.MovieType.POPULAR

fun RemoteResults<RemoteMovie>.setType(type: MovieType = POPULAR): List<RemoteMovie> {
    return results.map { it.copy(type = type) }
}