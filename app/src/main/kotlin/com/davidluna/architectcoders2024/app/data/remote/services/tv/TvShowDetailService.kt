package com.davidluna.architectcoders2024.app.data.remote.services.tv

import com.davidluna.architectcoders2024.app.data.remote.model.tv.RemoteTvShow
import retrofit2.http.GET
import retrofit2.http.Path

interface TvShowDetailService {
    @GET("tv/{tv_id}")
    suspend fun getTvShowDetail(@Path("tv_id") tvId: Int): RemoteTvShow
}