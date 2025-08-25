package com.davidluna.tmdb.core_framework.data.local.sources

import com.davidluna.tmdb.core_domain.usecases.GetCountryCodeUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AndroidLocationProvider @Inject constructor(
    private val resolver: CountryCodeResolver,
    private val getLocation: LocationService
) : GetCountryCodeUseCase {

    override fun invoke(): Flow<String> = flow {
        val region = getRegionCode()
        emit(region)
    }

    private suspend fun getRegionCode(): String =
        getLocation()?.let(resolver::invoke) ?: CountryCodeResolver.Companion.DEFAULT_COUNTRY_CODE
}