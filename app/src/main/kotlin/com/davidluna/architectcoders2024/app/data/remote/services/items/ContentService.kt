package com.davidluna.architectcoders2024.app.data.remote.services.items

import arrow.core.Either
import com.davidluna.architectcoders2024.app.data.remote.model.RemoteError
import com.davidluna.architectcoders2024.app.data.remote.model.movies.RemoteContent
import com.davidluna.architectcoders2024.app.data.remote.model.movies.RemoteResults
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ContentService {
    @GET("{endpoint}")
    suspend fun getContent(
        @Path("endpoint") endpoint: String,
        @Query("page") page: Int
    ): Either<RemoteError, RemoteResults<RemoteContent>>

}
