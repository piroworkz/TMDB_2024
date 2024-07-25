package com.davidluna.architectcoders2024.framework.remote.services

import arrow.core.Either
import com.davidluna.architectcoders2024.framework.remote.model.RemoteError
import com.davidluna.architectcoders2024.framework.remote.media.RemoteMedia
import com.davidluna.architectcoders2024.framework.remote.media.RemoteResults
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
