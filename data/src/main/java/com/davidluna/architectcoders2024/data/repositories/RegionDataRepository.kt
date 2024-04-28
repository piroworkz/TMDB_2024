package com.davidluna.architectcoders2024.data.repositories

import com.davidluna.architectcoders2024.data.sources.RegionDataSource
import com.davidluna.architectcoders2024.usecases.repositories.RegionRepository
import javax.inject.Inject

class RegionDataRepository @Inject constructor(
    private val location: RegionDataSource
) : RegionRepository {
    override suspend fun getCountryCode(): String = location.getCountryCode()
}
