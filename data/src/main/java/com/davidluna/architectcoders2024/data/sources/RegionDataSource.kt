package com.davidluna.architectcoders2024.data.sources

interface RegionDataSource {
    suspend fun getCountryCode(): String
}