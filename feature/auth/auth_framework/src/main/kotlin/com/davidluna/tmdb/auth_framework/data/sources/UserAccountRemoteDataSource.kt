package com.davidluna.tmdb.auth_framework.data.sources

import arrow.core.Either
import com.davidluna.tmdb.auth_domain.usecases.FetchUserAccountUseCase
import com.davidluna.tmdb.auth_framework.data.local.database.dao.AccountDao
import com.davidluna.tmdb.auth_framework.data.local.database.entities.RoomUserAccount
import com.davidluna.tmdb.auth_framework.data.remote.UserAccountService
import com.davidluna.tmdb.auth_framework.data.remote.model.RemoteUserAccountDetail
import com.davidluna.tmdb.core_domain.entities.AppError
import com.davidluna.tmdb.core_domain.entities.tryCatch
import com.davidluna.tmdb.core_framework.data.remote.model.buildModel
import com.davidluna.tmdb.core_framework.data.remote.model.toAppError
import javax.inject.Inject

class UserAccountRemoteDataSource @Inject constructor(
    private val remote: UserAccountService,
    private val local: AccountDao
) : FetchUserAccountUseCase {

    override suspend fun invoke(): Either<AppError, Unit> = tryCatch {
        if (!local.hasAccount()) {
            remote.getAccount().fold(
                ifLeft = { throw it.toAppError() },
                ifRight = { local.insertAccount(it.toLocalStorage()) }
            )
        }
    }

    private fun RemoteUserAccountDetail.toLocalStorage(): RoomUserAccount = RoomUserAccount(
        userId = userId,
        name = name,
        username = username,
        avatarPath = avatar.tmdb.avatarPath.buildModel()
    )
}