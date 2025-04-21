package com.davidluna.tmdb.auth_framework.data.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.davidluna.tmdb.auth_framework.data.local.database.entities.RoomUserAccount
import kotlinx.coroutines.flow.Flow

@Dao
interface AccountDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAccount(account: RoomUserAccount): Long

    @Query("SELECT * FROM RoomUserAccount LIMIT 1")
    fun getAccount(): Flow<RoomUserAccount?>

    @Query("SELECT EXISTS(SELECT 1 FROM RoomUserAccount LIMIT 1)")
    suspend fun hasAccount(): Boolean

    @Query("DELETE FROM RoomUserAccount")
    suspend fun deleteAccount(): Int
}