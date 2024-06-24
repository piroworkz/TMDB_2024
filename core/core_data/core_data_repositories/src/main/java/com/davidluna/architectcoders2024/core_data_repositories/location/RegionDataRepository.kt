package com.davidluna.architectcoders2024.core_data_repositories.location

import com.davidluna.architectcoders2024.core_domain.core_usecases.location.RegionRepository
import javax.inject.Inject

class RegionDataRepository @Inject constructor(
    private val location: RegionDataSource
) : RegionRepository {
    override suspend fun getCountryCode(): String = location.getCountryCode()
}
