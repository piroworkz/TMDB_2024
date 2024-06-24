package com.davidluna.architectcoders2024.core_data_repositories.location

interface RegionDataSource {
    suspend fun getCountryCode(): String
}