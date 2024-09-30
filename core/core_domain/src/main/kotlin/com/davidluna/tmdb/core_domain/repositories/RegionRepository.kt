package com.davidluna.tmdb.core_domain.repositories

interface RegionRepository {
    suspend fun getCountryCode(): String
}