package com.example.apiresponse.api

import retrofit2.http.GET

interface ApiService {
    @GET("users")
    suspend fun getUsers(): List<ApiResponse>
}