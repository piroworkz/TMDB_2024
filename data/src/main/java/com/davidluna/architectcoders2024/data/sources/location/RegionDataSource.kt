package com.davidluna.architectcoders2024.data.sources.location

interface RegionDataSource {
    suspend fun getCountryCode(): String
}