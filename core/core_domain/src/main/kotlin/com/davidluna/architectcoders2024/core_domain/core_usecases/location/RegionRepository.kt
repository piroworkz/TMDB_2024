package com.davidluna.architectcoders2024.core_domain.core_usecases.location

interface RegionRepository {
    suspend fun getCountryCode(): String
}