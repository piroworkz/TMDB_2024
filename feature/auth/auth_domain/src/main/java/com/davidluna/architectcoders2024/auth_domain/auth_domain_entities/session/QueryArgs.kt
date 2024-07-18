package com.davidluna.architectcoders2024.auth_domain.auth_domain_entities.session

data class QueryArgs(
    val requestToken: String,
    val approved: Boolean
)
