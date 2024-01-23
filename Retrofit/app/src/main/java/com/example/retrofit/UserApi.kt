package com.example.retrofit

import retrofit2.http.GET

interface UserApi {
    @GET("/posts")
    suspend fun getUser(): List<User>
}



