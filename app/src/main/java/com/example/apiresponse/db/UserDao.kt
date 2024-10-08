package com.example.apiresponse.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface UserDao {

    @Query("SELECT * FROM UserEntity WHERE isArchived = 0")
    fun getAllUsers(): LiveData<List<UserEntity>>

    @Query("SELECT * FROM UserEntity WHERE isFavorite = 1 AND isArchived = 0")
    fun getFavoriteUsers(): LiveData<List<UserEntity>>

    @Query("SELECT * FROM UserEntity WHERE isArchived = 1")
    fun getArchivedUsers(): LiveData<List<UserEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: UserEntity)

    @Update
    suspend fun updateUser(user: UserEntity)
}