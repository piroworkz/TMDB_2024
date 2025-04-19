package com.davidluna.tmdb.core_domain.data.location

import com.davidluna.tmdb.core_domain.usecases.location.RegionRepository
import javax.inject.Inject

class RegionDataRepository @Inject constructor(
    private val location: com.davidluna.tmdb.core_domain.data.location.RegionDataSource
) : RegionRepository {
    override suspend fun getCountryCode(): String = location.getCountryCode()
}
