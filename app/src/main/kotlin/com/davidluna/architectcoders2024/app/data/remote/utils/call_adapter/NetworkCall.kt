package com.davidluna.architectcoders2024.app.data.remote.utils.call_adapter

import arrow.core.Either
import com.davidluna.architectcoders2024.app.data.toAppError
import com.davidluna.architectcoders2024.domain.AppError
import okhttp3.Request
import okhttp3.ResponseBody
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Converter
import retrofit2.Response


class NetworkCall<L : Any, R : Any>(
    private val call: Call<R>,
    private val errorConverter: Converter<ResponseBody, L>
) : Call<Either<L, R>> {

    override fun clone(): Call<Either<L, R>> =
        NetworkCall(call.clone(), errorConverter)

    override fun execute(): Response<Either<L, R>> {
        throw UnsupportedOperationException("Execute is not supported in NetworkCall")
    }

    override fun enqueue(callback: Callback<Either<L, R>>) {
        return call.enqueue(
            object : Callback<R> {
                override fun onResponse(call: Call<R>, response: Response<R>) {
                    callback.toAppResponse(response)
                }

                override fun onFailure(call: Call<R>, throwable: Throwable) {
                    throwable.printStackTrace()
                    throw throwable.toAppError()
                }

            }
        )
    }

    override fun isExecuted(): Boolean = call.isExecuted

    override fun cancel() = call.cancel()

    override fun isCanceled(): Boolean = call.isCanceled

    override fun request(): Request = call.request()

    override fun timeout(): Timeout = call.timeout()

    private fun Callback<Either<L, R>>.toAppResponse(response: Response<R>) {
        val body = response.body()
        val error = response.errorBody()
        if (response.isSuccessful && body != null) {
            onResponse(this@NetworkCall, Response.success(Either.Right(body)))
        } else {
            val errorBody = when {
                error == null -> null
                error.contentLength() == 0L -> null
                else -> try {
                    errorConverter.convert(error)
                } catch (e: Exception) {
                    e.printStackTrace()
                    null
                }
            }
            if (errorBody != null) {
                onResponse(this@NetworkCall, Response.success(Either.Left(errorBody)))
            } else {
                throw AppError.Network(
                    code = response.code(),
                    description = response.message(),
                    successful = false
                )
            }
        }
    }
}
