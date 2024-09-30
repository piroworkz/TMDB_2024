package com.davidluna.tmdb.core_domain.data.location

import com.davidluna.tmdb.core_domain.repositories.RegionRepository

class RegionDataRepository (
    private val location: RegionDataSource
) : RegionRepository {
    override suspend fun getCountryCode(): String = location.getCountryCode()
}
