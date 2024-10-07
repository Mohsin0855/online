package com.example.apiresponse.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apiresponse.api.ApiService
import com.example.apiresponse.data.User
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class UserViewModel : ViewModel() {
    private val _users = MutableLiveData<List<User>>()
    val users : LiveData<List<User>> get() = _users
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
    fun fetchUsers() {

        viewModelScope.launch {
            try {
                val userList = apiService.getUsers()
                Log.d("UserViewModel", "Fetched users: $userList")
                _users.value = userList
            } catch (e: Exception) {
                Log.e("UserViewModel", "Error fetching users: ${e.message}")
                e.printStackTrace()
            }
        }
    }
}