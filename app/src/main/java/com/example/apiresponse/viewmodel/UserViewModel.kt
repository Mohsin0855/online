package com.example.apiresponse.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apiresponse.api.ApiService
import com.example.apiresponse.db.UserEntity
import com.example.apiresponse.repo.UserRepository
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class UserViewModel(private val repository: UserRepository) : ViewModel() {

    private val _users = MutableLiveData<List<UserEntity>>()
    val users: LiveData<List<UserEntity>> get() = _users
    val favoriteUsers: LiveData<List<UserEntity>> = repository.favoriteUsers
    val archivedUsers: LiveData<List<UserEntity>> = repository.archivedUsers

    private val logging = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val client = OkHttpClient.Builder()
        .addInterceptor(logging)
        .build()

    private val apiService: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl("https://fake-json-api.mock.beeceptor.com/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    fun fetchAllUsers() {
        viewModelScope.launch {
            repository.getAllUsers().observeForever { userList ->
                _users.value = userList
            }
        }
    }

    fun fetchUsers() {
        viewModelScope.launch {
            try {
                val apiResponseList = apiService.getUsers()
                Log.d("UserViewModel", "Fetched users: $apiResponseList")

                val userList = apiResponseList.map { apiResponse ->
                    UserEntity(
                        id = apiResponse.id,
                        name = apiResponse.name,
                        email = apiResponse.email,
                        photoUrl = apiResponse.photo,
                        isFavorite = false
                    )
                }

                userList.forEach { user ->
                    insertUser(user)
                }

                _users.value = userList

            } catch (e: Exception) {
                Log.e("UserViewModel", "Error fetching users: ${e.message}")
            }
        }
    }

    fun insertUser(user: UserEntity) {
        viewModelScope.launch {
            repository.insertUser(user)
        }
    }

    fun toggleFavorite(user: UserEntity) {
        viewModelScope.launch {
            val newFavoriteStatus = !user.isFavorite
            repository.updateUser(user.copy(isFavorite = newFavoriteStatus))
        }
    }

    fun archiveUser(user: UserEntity) {
        viewModelScope.launch {
            repository.updateUser(user.copy(isArchived = true))
        }
    }

    fun renameUser(user: UserEntity, newName: String) {
        viewModelScope.launch {
            repository.updateUser(user.copy(name = newName))
        }
    }

}