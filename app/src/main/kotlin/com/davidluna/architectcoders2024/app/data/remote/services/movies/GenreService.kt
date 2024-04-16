package com.davidluna.architectcoders2024.app.data.remote.services.movies

import com.davidluna.architectcoders2024.app.data.remote.model.movies.RemoteGenres
import retrofit2.http.GET

interface GenreService {
    @GET("genre/movie/list")
    suspend fun getMovieGenres(): RemoteGenres

    @GET("genre/tv/list")
    suspend fun getTvShowGenres(): RemoteGenres
}