package com.davidluna.tmdb.core_domain.usecases.location

interface RegionRepository {
    suspend fun getCountryCode(): String
}