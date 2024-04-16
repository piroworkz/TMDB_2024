package com.davidluna.architectcoders2024.app.data.remote.services.movies

import com.davidluna.architectcoders2024.app.data.remote.model.movies.RemoteCertificationsResponse
import retrofit2.http.GET

interface CertificationsService {
    @GET("certification/movie/list")
    suspend fun getMovieCertifications(): RemoteCertificationsResponse

    @GET("certification/tv/list")
    suspend fun getTvShowCertifications(): RemoteCertificationsResponse

}

