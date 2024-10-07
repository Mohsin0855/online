package com.example.apiresponse.api

import com.example.apiresponse.data.User
import retrofit2.http.GET

interface ApiService {
    @GET("users")
    suspend fun getUsers(): List<User>
}