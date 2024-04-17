package com.davidluna.architectcoders2024.app.data.remote

import com.davidluna.architectcoders2024.app.data.remote.services.authentication.AuthenticationService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    private val client = HttpLoggingInterceptor().run {
        level = HttpLoggingInterceptor.Level.BODY
        OkHttpClient.Builder()
            .addInterceptor(MoviesInterceptor())
            .addInterceptor(this)
            .build()
    }

    private val gson: Gson = GsonBuilder()
        .setLenient()
        .setPrettyPrinting()
        .create()

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/")
        .client(client)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    val authenticationService: AuthenticationService =
        retrofit.create(AuthenticationService::class.java)
}