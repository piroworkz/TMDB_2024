package com.davidluna.tmdb.core_data.repositories

import com.davidluna.tmdb.core_data.framework.local.CountryCodeDatasource
import com.davidluna.tmdb.core_domain.repositories.RegionRepository

class RegionDataRepository (
    private val location: CountryCodeDatasource
) : RegionRepository {
    override suspend fun getCountryCode(): String = location.getCountryCode()
}