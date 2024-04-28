package com.davidluna.architectcoders2024.usecases.repositories

interface RegionRepository {
    suspend fun getCountryCode(): String
}