package com.davidluna.architectcoders2024.usecases.location

import com.davidluna.architectcoders2024.usecases.repositories.RegionRepository
import javax.inject.Inject

class GetCountryCodeUseCase @Inject constructor(
    private val repository: RegionRepository,
) {
    suspend operator fun invoke(): String =
        repository.getCountryCode()
}