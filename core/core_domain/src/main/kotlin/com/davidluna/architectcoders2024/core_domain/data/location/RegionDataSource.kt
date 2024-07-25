package com.davidluna.architectcoders2024.core_domain.data.location

interface RegionDataSource {
    suspend fun getCountryCode(): String
}