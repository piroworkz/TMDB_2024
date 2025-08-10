package com.davidluna.tmdb.auth_framework.di

import com.davidluna.tmdb.auth_domain.usecases.CloseSessionUseCase
import com.davidluna.tmdb.auth_domain.usecases.FetchUserAccountUseCase
import com.davidluna.tmdb.auth_domain.usecases.GetSessionUseCase
import com.davidluna.tmdb.auth_domain.usecases.GetUserAccountUseCase
import com.davidluna.tmdb.auth_domain.usecases.LoginAsGuest
import com.davidluna.tmdb.auth_domain.usecases.LoginWithCredentials
import com.davidluna.tmdb.auth_domain.usecases.ValidateGuestSessionUseCase
import com.davidluna.tmdb.auth_domain.usecases.ValidateInputUseCase
import com.davidluna.tmdb.auth_framework.data.local.QueryParametersSnapshot
import com.davidluna.tmdb.auth_framework.data.local.TextInputValidator
import com.davidluna.tmdb.auth_framework.data.sources.CloseSessionDataSource
import com.davidluna.tmdb.auth_framework.data.sources.GuestSessionValidator
import com.davidluna.tmdb.auth_framework.data.sources.GuestUserAuthenticationRepository
import com.davidluna.tmdb.auth_framework.data.sources.LocalSessionDataSource
import com.davidluna.tmdb.auth_framework.data.sources.LocalUserAccountDataSource
import com.davidluna.tmdb.auth_framework.data.sources.RegisteredUserAuthenticationRepository
import com.davidluna.tmdb.auth_framework.data.sources.UserAccountRemoteDataSource
import com.davidluna.tmdb.core_framework.data.remote.interceptors.ParametersSnapshot
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class AuthDataModule {
    @Binds abstract fun bindCloseSessionUseCase(source: CloseSessionDataSource): CloseSessionUseCase
    @Binds abstract fun bindFetchUserAccountUseCase(source: UserAccountRemoteDataSource): FetchUserAccountUseCase
    @Binds abstract fun bindGetSessionUseCase(source: LocalSessionDataSource): GetSessionUseCase
    @Binds abstract fun bindGetUserAccountUseCase(source: LocalUserAccountDataSource): GetUserAccountUseCase
    @Binds abstract fun bindLoginGuestUseCase(source: GuestUserAuthenticationRepository): LoginAsGuest
    @Binds abstract fun bindLoginUseCase(source: RegisteredUserAuthenticationRepository): LoginWithCredentials
    @Binds abstract fun bindParametersSnapshot(source: QueryParametersSnapshot): ParametersSnapshot
    @Binds abstract fun bindValidateGuestSessionUseCase(source: GuestSessionValidator): ValidateGuestSessionUseCase
    @Binds abstract fun bindValidateInputUseCase(source: TextInputValidator): ValidateInputUseCase
}