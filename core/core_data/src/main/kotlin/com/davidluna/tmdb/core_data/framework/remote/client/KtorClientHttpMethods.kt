package com.davidluna.tmdb.core_data.framework.remote.client

import arrow.core.Either
import com.davidluna.tmdb.core_data.framework.remote.model.RemoteError
import com.davidluna.tmdb.core_domain.entities.errors.AppError
import com.davidluna.tmdb.core_domain.entities.errors.toAppError
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.url

suspend inline fun <reified T : Any> HttpClient.get(
    urlString: String,
    requestBuilder: HttpRequestBuilder.() -> Unit = {},
): Either<AppError, T> = try {
    get { url(urlString); requestBuilder() }.call.response.let { response ->
        if (response.status.value in 200..299) {
            Either.Right(response.body<T>())
        } else {
            Either.Left(response.body<RemoteError>().toAppError())
        }
    }
} catch (e: Exception) {
    Either.Left(e.toAppError())
}

suspend inline fun <reified T : Any> HttpClient.post(
    urlString: String,
    requestBuilder: HttpRequestBuilder.() -> Unit = {},
): Either<AppError, T> = try {
    post { url(urlString); requestBuilder() }.call.response.let { response ->
        if (response.status.value in 200..299) {
            Either.Right(response.body<T>())
        } else {
            Either.Left(response.body<RemoteError>().toAppError())
        }
    }
} catch (e: Exception) {
    Either.Left(e.toAppError())
}

