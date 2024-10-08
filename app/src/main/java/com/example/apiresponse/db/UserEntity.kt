package com.example.apiresponse.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "UserEntity")
data class UserEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val email: String,
    val photoUrl: String,
    val isFavorite: Boolean = false,
    val isArchived: Boolean = false // New field for archived users
)