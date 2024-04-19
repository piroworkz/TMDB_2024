package com.davidluna.architectcoders2024.app.data.remote

import com.davidluna.architectcoders2024.app.data.remote.call_adapter.NetworkCallAdapterFactory
import com.davidluna.architectcoders2024.app.data.remote.services.authentication.AuthenticationService
import com.davidluna.architectcoders2024.app.data.remote.services.movies.MoviesService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit

class Client(interceptor: MoviesInterceptor) {

    private val callAdapterFactory = NetworkCallAdapterFactory()

    private val client = HttpLoggingInterceptor().run {
        level = HttpLoggingInterceptor.Level.BODY
        OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .addInterceptor(this)
            .build()
    }

    private val json = Json {
        ignoreUnknownKeys = true
        isLenient = true
        prettyPrint = true
    }

    private val converterFactory: Converter.Factory =
        json.asConverterFactory("application/json".toMediaType())

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/")
        .client(client)
        .addCallAdapterFactory(callAdapterFactory)
        .addConverterFactory(converterFactory)
        .build()

    val authenticationService: AuthenticationService =
        retrofit.create(AuthenticationService::class.java)

    val moviesService: MoviesService =
        retrofit.create(MoviesService::class.java)
}