package com.example.apiresponse.repo

import androidx.lifecycle.LiveData
import com.example.apiresponse.db.UserDao
import com.example.apiresponse.db.UserEntity

class UserRepository(private val userDao: UserDao) {

    fun getAllUsers(): LiveData<List<UserEntity>> = userDao.getAllUsers()

    val favoriteUsers: LiveData<List<UserEntity>> = userDao.getFavoriteUsers()

    val archivedUsers: LiveData<List<UserEntity>> = userDao.getArchivedUsers()

    suspend fun insertUser(user: UserEntity) {
        userDao.insertUser(user)
    }

    suspend fun updateUser(user: UserEntity) {
        userDao.updateUser(user)
    }
}