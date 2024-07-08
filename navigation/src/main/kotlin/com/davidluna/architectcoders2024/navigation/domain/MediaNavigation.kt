package com.davidluna.architectcoders2024.navigation.domain

import kotlinx.serialization.Serializable

@Serializable
sealed interface MediaNavigation : Destination {
    @Serializable
    data object Init : MediaNavigation

    @Serializable
    data class Movies(
        val isTopLevel: Boolean = true
    ) : MediaNavigation

    @Serializable
    data class Detail(
        val movieId: Int
    ) : MediaNavigation

}


