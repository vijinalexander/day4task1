package com.example.day_4task1

import retrofit2.http.GET

interface HttpApiService {
    @GET("books")
    suspend fun getMyBook():List<Detail>

}