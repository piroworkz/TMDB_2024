package com.davidluna.architectcoders2024.app.data.remote.services.movies

import com.davidluna.architectcoders2024.app.data.remote.model.movies.RemoteMovie
import com.davidluna.architectcoders2024.app.data.remote.model.movies.RemoteResults
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesService {
    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(@Query("page") page: Int): RemoteResults<RemoteMovie>

    @GET("movie/popular")
    suspend fun getPopularMovies(@Query("page") page: Int): RemoteResults<RemoteMovie>

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(@Query("page") page: Int): RemoteResults<RemoteMovie>

    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(@Query("page") page: Int): RemoteResults<RemoteMovie>

}
