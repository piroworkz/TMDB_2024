package com.davidluna.architectcoders2024.app.data.remote.call_adapter

import arrow.core.Either
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

class NetworkCallAdapterFactory() : CallAdapter.Factory() {
    override fun get(
        returnType: Type,
        annotations: Array<out Annotation>,
        retrofit: Retrofit
    ): CallAdapter<*, *>? {
        if (Call::class.java != getRawType(returnType)) {
            return null
        }

        check(returnType is ParameterizedType) {
            "return type must be parameterized"
        }

        val responseType = getParameterUpperBound(0, returnType)
        if (getRawType(responseType) != Either::class.java) {
            return null
        }

        check(responseType is ParameterizedType) {
            "responseType must be parameterized"
        }

        val rightBody = getParameterUpperBound(1, responseType)
        val leftBody = getParameterUpperBound(0, responseType)
        val errorConverter = retrofit.nextResponseBodyConverter<Any>(null, leftBody, annotations)

        return NetworkCallAdapter<Any, Any>(rightBody, errorConverter)

    }
}