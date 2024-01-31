package com.example.mvvm

import retrofit2.http.GET
import retrofit2.http.Path

interface UserApi {
    @GET("/posts")
    suspend fun getUser(): List<User>

    @GET("/posts/{id}")
    suspend fun getUserById(@Path("id") id: Int): User
}