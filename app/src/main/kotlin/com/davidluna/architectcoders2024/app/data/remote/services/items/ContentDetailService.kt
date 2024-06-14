package com.davidluna.architectcoders2024.app.data.remote.services.items

import arrow.core.Either
import com.davidluna.architectcoders2024.app.data.remote.model.RemoteError
import com.davidluna.architectcoders2024.app.data.remote.model.movies.RemoteContentDetail
import com.davidluna.architectcoders2024.app.data.remote.model.movies.RemoteCredits
import com.davidluna.architectcoders2024.app.data.remote.model.movies.RemoteImages
import com.davidluna.architectcoders2024.app.data.remote.model.movies.RemoteVideos
import retrofit2.http.GET
import retrofit2.http.Path

interface ContentDetailService {

    @GET("{endpoint}")
    suspend fun getDetailById(@Path("endpoint") endpoint: String): Either<RemoteError, RemoteContentDetail>

    @GET("{endpoint}/credits")
    suspend fun getCreditsById(@Path("endpoint") endpoint: String): Either<RemoteError, RemoteCredits>

    @GET("{endpoint}/images")
    suspend fun getImagesById(@Path("endpoint") endpoint: String): Either<RemoteError, RemoteImages>

    @GET("{endpoint}/videos")
    suspend fun getVideos(@Path("endpoint") endpoint: String): Either<RemoteError, RemoteVideos>

}