package com.davidluna.tmdb.auth_domain.entities.session

data class QueryArgs(
    val requestToken: String,
    val approved: Boolean
)
