package com.davidluna.tmdb.core_domain.usecases

import com.davidluna.tmdb.core_domain.repositories.RegionRepository

class GetCountryCodeUseCase (
    private val repository: RegionRepository,
) {
    suspend operator fun invoke(): String =
        repository.getCountryCode()
}