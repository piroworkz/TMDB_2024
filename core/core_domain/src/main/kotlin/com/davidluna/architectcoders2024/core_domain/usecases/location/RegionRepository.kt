package com.davidluna.architectcoders2024.core_domain.usecases.location

interface RegionRepository {
    suspend fun getCountryCode(): String
}