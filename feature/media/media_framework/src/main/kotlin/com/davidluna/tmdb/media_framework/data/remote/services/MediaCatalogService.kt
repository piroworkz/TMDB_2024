package com.davidluna.tmdb.media_framework.data.remote.services

import arrow.core.Either
import com.davidluna.tmdb.core_framework.data.remote.model.RemoteError
import com.davidluna.tmdb.media_framework.data.remote.media.RemoteMedia
import com.davidluna.tmdb.media_framework.data.remote.media.RemoteResults
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MediaCatalogService {

    @GET("{endpoint}")
    suspend fun getMediaCatalog(
        @Path("endpoint", encoded = true) endpoint: String,
        @Query("page") page: Int
    ): Either<RemoteError, RemoteResults<RemoteMedia>>

}
