package com.example.mvvm

import retrofit2.http.GET
interface UserApi {
        @GET("/posts")
        suspend fun getUser(): List<User>
    }