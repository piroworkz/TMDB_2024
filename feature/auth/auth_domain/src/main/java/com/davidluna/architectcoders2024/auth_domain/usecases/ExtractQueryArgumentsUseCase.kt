package com.davidluna.architectcoders2024.auth_domain.usecases

import com.davidluna.architectcoders2024.auth_domain.entities.session.QueryArgs
import javax.inject.Inject


class ExtractQueryArgumentsUseCase @Inject constructor() {

    operator fun invoke(query: String): QueryArgs {
        return try {
            val argsList = query.split("&").map {
                it.split("=").last()
            }
            QueryArgs(
                requestToken = argsList[0],
                approved = argsList[1].toBoolean()
            )
        } catch (e: Exception) {
            QueryArgs("", false)
        }
    }
}