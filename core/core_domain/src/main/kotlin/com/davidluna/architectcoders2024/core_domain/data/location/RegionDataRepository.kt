package com.davidluna.architectcoders2024.core_domain.data.location

import com.davidluna.architectcoders2024.core_domain.usecases.location.RegionRepository
import javax.inject.Inject

class RegionDataRepository @Inject constructor(
    private val location: RegionDataSource
) : RegionRepository {
    override suspend fun getCountryCode(): String = location.getCountryCode()
}
