package com.example.retrofit

import retrofit2.http.GET

interface QuoteApi {
    @GET("/posts")
    suspend fun getQuote(): List<Quote>
}
