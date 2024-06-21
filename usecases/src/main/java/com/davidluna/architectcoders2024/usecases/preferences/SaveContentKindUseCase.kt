package com.davidluna.architectcoders2024.usecases.preferences

import com.davidluna.architectcoders2024.domain.ContentKind
import com.davidluna.architectcoders2024.usecases.repositories.LocalPreferencesRepository
import javax.inject.Inject

class SaveContentKindUseCase @Inject constructor(private val repository: LocalPreferencesRepository) {
    suspend operator fun invoke(contentKind: ContentKind): Unit =
        repository.saveContentKind(contentKind)
}