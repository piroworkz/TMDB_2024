package com.davidluna.tmdb.core_framework.data.remote.call_adapter

import arrow.core.Either
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Converter
import java.lang.reflect.Type

class NetworkCallAdapter<L : Any, R : Any>(
    private val successType: Type,
    private val errorConverter: Converter<ResponseBody, L>
) : CallAdapter<R, Call<Either<L, R>>> {
    override fun responseType(): Type {
        return successType
    }

    override fun adapt(p0: Call<R>): Call<Either<L, R>> {
        return NetworkCall(p0, errorConverter)
    }
}