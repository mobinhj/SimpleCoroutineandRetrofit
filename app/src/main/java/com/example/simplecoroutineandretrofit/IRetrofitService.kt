package com.example.simplecoroutineandretrofit

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.POST
import retrofit2.http.Url

interface IRetrofitService {
    @GET("/posts")
    suspend fun getPosts(): Response<List<Post>>
    @GET("/posts")
    suspend fun getPosts1(): Response<List<Post>>
    @GET("/posts")
    suspend fun getPosts2(): Response<List<Post>>
}