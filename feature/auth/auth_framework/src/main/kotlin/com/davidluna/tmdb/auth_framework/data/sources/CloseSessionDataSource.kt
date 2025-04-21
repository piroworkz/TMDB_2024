package com.davidluna.tmdb.auth_framework.data.sources

import arrow.core.Either
import com.davidluna.tmdb.auth_domain.usecases.CloseSessionUseCase
import com.davidluna.tmdb.auth_framework.data.local.database.dao.AccountDao
import com.davidluna.tmdb.auth_framework.data.local.database.dao.SessionDao
import com.davidluna.tmdb.core_domain.entities.AppError
import com.davidluna.tmdb.core_domain.entities.tryCatch
import javax.inject.Inject

class CloseSessionDataSource @Inject constructor(
    private val accountDao: AccountDao,
    private val sessionDao: SessionDao,
) : CloseSessionUseCase {
    override suspend operator fun invoke(): Either<AppError, Boolean> = tryCatch {
        val isSessionDeleted = sessionDao.deleteSession() > 0
        val isAccountDeleted = accountDao.deleteAccount() > 0
        isSessionDeleted && isAccountDeleted
    }
}