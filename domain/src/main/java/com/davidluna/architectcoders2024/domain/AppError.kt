package com.davidluna.architectcoders2024.domain

sealed class AppError(
    val statusCode: Int = 0,
    val statusMessage: String = "",
    val success: Boolean = false
) : Throwable() {

    data class Serialization(
        val code: Int,
        val description: String,
        val successful: Boolean
    ) : AppError(code, description, successful)


    data class Unknown(
        val code: Int,
        val description: String,
        val successful: Boolean
    ) : AppError(code, description, successful)

    data class Network(
        val code: Int,
        val description: String,
        val successful: Boolean
    ) : AppError(code, description, successful)

    data class IO(
        val code: Int,
        val description: String,
        val successful: Boolean
    ) : AppError(code, description, successful)
}