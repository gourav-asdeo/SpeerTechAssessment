package com.app.speertechassessment.network

import com.app.speertechassessment.models.User
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("users/{name}")
    suspend fun getUser(@Path("name") name:String): Response<User>

    @GET("users/{name}/followers")
    suspend fun getUserFollowers(@Path("name") name:String): Response<List<User>>

    @GET("users/{name}/following")
    suspend fun getUserFollowing(@Path("name") name:String): Response<List<User>>

}