package com.davidluna.architectcoders2024.data.repositories.location

import com.davidluna.architectcoders2024.data.sources.location.RegionDataSource
import com.davidluna.architectcoders2024.usecases.repositories.RegionRepository
import javax.inject.Inject

class RegionDataRepository @Inject constructor(
    private val location: RegionDataSource
) : RegionRepository {
    override suspend fun getCountryCode(): String = location.getCountryCode()
}
