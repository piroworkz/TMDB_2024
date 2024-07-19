package com.davidluna.architectcoders2024.test_shared_framework.integration.di

import com.davidluna.architectcoders2024.auth_domain.auth_domain_usecases.session.CreateGuestSessionIdUseCase
import com.davidluna.architectcoders2024.auth_domain.auth_domain_usecases.session.CreateRequestTokenUseCase
import com.davidluna.architectcoders2024.auth_domain.auth_domain_usecases.session.CreateSessionIdUseCase
import com.davidluna.architectcoders2024.auth_domain.auth_domain_usecases.session.ExtractQueryArgumentsUseCase
import com.davidluna.architectcoders2024.auth_domain.auth_domain_usecases.session.GetUserAccountUseCase
import com.davidluna.architectcoders2024.auth_domain.auth_domain_usecases.session.LoginViewModelUseCases
import com.davidluna.architectcoders2024.core_domain.core_usecases.datastore.CloseSessionUseCase
import com.davidluna.architectcoders2024.core_domain.core_usecases.datastore.GetContentKindUseCase
import com.davidluna.architectcoders2024.core_domain.core_usecases.datastore.SaveContentKindUseCase
import com.davidluna.architectcoders2024.core_domain.core_usecases.datastore.SessionIdUseCase
import com.davidluna.architectcoders2024.core_domain.core_usecases.datastore.UserAccountUseCase
import com.davidluna.architectcoders2024.media_domain.media_domain_usecases.FormatDateUseCase
import com.davidluna.architectcoders2024.media_domain.media_domain_usecases.GetMediaCastUseCase
import com.davidluna.architectcoders2024.media_domain.media_domain_usecases.GetMediaCatalogUseCase
import com.davidluna.architectcoders2024.media_domain.media_domain_usecases.GetMediaDetailsUseCase
import com.davidluna.architectcoders2024.media_domain.media_domain_usecases.GetMediaImagesUseCase
import com.davidluna.architectcoders2024.videos_domain.videos_domain_usecases.GetVideosUseCase

class UseCasesModuleDI {

    private val createRequestToken: CreateRequestTokenUseCase by lazy {
        CreateRequestTokenUseCase(RepositoriesModuleDI().sessionRepository)
    }

    private val createSessionId: CreateSessionIdUseCase by lazy {
        CreateSessionIdUseCase(RepositoriesModuleDI().sessionRepository)
    }
    private val createGuestSessionId: CreateGuestSessionIdUseCase by lazy {
        CreateGuestSessionIdUseCase(RepositoriesModuleDI().sessionRepository)
    }
    private val getUserAccount: GetUserAccountUseCase by lazy {
        GetUserAccountUseCase(RepositoriesModuleDI().sessionRepository)
    }
    private val sessionId: SessionIdUseCase by lazy {
        SessionIdUseCase(RepositoriesModuleDI().preferencesRepository)
    }
    private val extractQueryArguments: ExtractQueryArgumentsUseCase by lazy {
        ExtractQueryArgumentsUseCase()
    }
    val loginViewModelUseCases: LoginViewModelUseCases by lazy {
        LoginViewModelUseCases(
            createRequestToken,
            createSessionId,
            createGuestSessionId,
            getUserAccount,
            sessionId,
            extractQueryArguments
        )
    }

    val userAccountUseCase: UserAccountUseCase by lazy {
        UserAccountUseCase(RepositoriesModuleDI().preferencesRepository)
    }

    val saveContentKindUseCase: SaveContentKindUseCase by lazy {
        SaveContentKindUseCase(RepositoriesModuleDI().preferencesRepository)
    }

    val getContentKindUseCase: GetContentKindUseCase by lazy {
        GetContentKindUseCase(RepositoriesModuleDI().preferencesRepository)
    }

    val closeSessionUseCase: CloseSessionUseCase by lazy {
        CloseSessionUseCase(RepositoriesModuleDI().preferencesRepository)
    }

    private val formatDateUseCase by lazy {
        FormatDateUseCase()
    }

    val getMediaCatalogUseCase: GetMediaCatalogUseCase by lazy {
        GetMediaCatalogUseCase(RepositoriesModuleDI().mediaRepository)
    }

    val getMediaImagesUseCase: GetMediaImagesUseCase by lazy {
        GetMediaImagesUseCase(RepositoriesModuleDI().movieDetailsRepository)
    }

    val getVideosUseCase: GetVideosUseCase by lazy {
        GetVideosUseCase(RepositoriesModuleDI().videosRepository)
    }

    val getMediaDetailsUseCase: GetMediaDetailsUseCase by lazy {
        GetMediaDetailsUseCase(
            RepositoriesModuleDI().movieDetailsRepository,
            formatDateUseCase,
            getVideosUseCase
        )
    }

    val getMediaCastUseCase: GetMediaCastUseCase by lazy {
        GetMediaCastUseCase(RepositoriesModuleDI().movieDetailsRepository)
    }

}