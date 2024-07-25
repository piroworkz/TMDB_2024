package com.davidluna.architectcoders2024.core_domain.usecases.location

import javax.inject.Inject

class GetCountryCodeUseCase @Inject constructor(
    private val repository: RegionRepository,
) {
    suspend operator fun invoke(): String =
        repository.getCountryCode()
}