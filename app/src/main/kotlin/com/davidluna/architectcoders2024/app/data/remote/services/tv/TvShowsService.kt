package com.davidluna.architectcoders2024.app.data.remote.services.tv

import com.davidluna.architectcoders2024.app.data.remote.model.movies.RemoteResults
import com.davidluna.architectcoders2024.app.data.remote.model.tv.RemoteTvShow
import retrofit2.http.GET
import retrofit2.http.Query

interface TvShowsService {

    @GET("tv/airing_today")
    suspend fun getAiringToday(@Query("page") page: Int): RemoteResults<RemoteTvShow>

    @GET("tv/on_the_air")
    suspend fun getOnTheAir(@Query("page") page: Int): RemoteResults<RemoteTvShow>

    @GET("tv/popular")
    suspend fun getPopular(@Query("page") page: Int): RemoteResults<RemoteTvShow>

    @GET("tv/top_rated")
    suspend fun getTopRated(@Query("page") page: Int): RemoteResults<RemoteTvShow>
}



