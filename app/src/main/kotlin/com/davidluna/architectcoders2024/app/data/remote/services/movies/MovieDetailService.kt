package com.davidluna.architectcoders2024.app.data.remote.services.movies

import com.davidluna.architectcoders2024.app.data.remote.model.movies.RemoteMovie
import com.davidluna.architectcoders2024.app.data.remote.model.movies.RemoteResults
import com.davidluna.architectcoders2024.app.data.remote.model.movies.RemoteReview
import com.davidluna.architectcoders2024.app.data.remote.model.movies.RemoteImages
import com.davidluna.architectcoders2024.app.data.remote.model.movies.RemoteMovieCredits
import com.davidluna.architectcoders2024.app.data.remote.model.movies.RemoteMovieDetail
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieDetailService {
    @GET("movie/{movie_id}")
    suspend fun getMovieDetail(@Path("movie_id") movieId: Int): RemoteMovieDetail

    @GET("movie/{movie_id}/credits")
    suspend fun getMovieCredits(@Path("movie_id") movieId: Int): RemoteMovieCredits

    @GET("movie/{movie_id}/images")
    suspend fun getMovieImages(@Path("movie_id") movieId: Int): RemoteImages

    @GET("movie/{movie_id}/recommendations")
    suspend fun getMovieRecommendations(
        @Path("movie_id") movieId: Int,
        @Query("page") page: Int
    ): RemoteResults<RemoteMovie>

    @GET("movie/{movie_id}/reviews")
    suspend fun getMovieReviews(
        @Path("movie_id") movieId: Int,
        @Query("page") page: Int
    ): RemoteResults<RemoteReview>

    @GET("movie/{movie_id}/similar")
    suspend fun getSimilarMovies(
        @Path("movie_id") movieId: Int,
        @Query("page") page: Int
    ): RemoteResults<RemoteMovie>
}