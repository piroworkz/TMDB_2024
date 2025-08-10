package com.davidluna.tmdb.auth_framework.data.sources

import com.davidluna.tmdb.auth_domain.entities.UserAccount
import com.davidluna.tmdb.auth_domain.usecases.GetUserAccountUseCase
import com.davidluna.tmdb.auth_framework.data.local.database.dao.AccountDao
import com.davidluna.tmdb.auth_framework.data.local.database.entities.RoomUserAccount
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LocalUserAccountDataSource @Inject constructor(
    private val local: AccountDao
) : GetUserAccountUseCase {
    override fun invoke(): Flow<UserAccount?> {
        return local.getAccount().map { it?.toDomain() }
    }

    private fun RoomUserAccount.toDomain(): UserAccount = UserAccount(
        userId = userId,
        name = name,
        username = username,
        avatarPath = avatarPath
    )
}