package com.davidluna.tmdb.core_domain.usecases

import kotlinx.coroutines.flow.Flow

fun interface GetCountryCodeUseCase : () -> Flow<String>