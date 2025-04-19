package com.davidluna.tmdb.core_domain.data.location

interface RegionDataSource {
    suspend fun getCountryCode(): String
}