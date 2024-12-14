package com.example.movieapp.api

import com.example.movieapp.data.User
import com.example.movieapp.data.Video
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    // Get a user by ID
    @GET("users/{id}")
    fun getUser(@Path("id") userId: String): Call<User>

    @GET("users/all")
    fun getAllUsers(): Call<List<User>>

    // Add a new user
    @POST("users")
    fun addUser(@Body user: User): Call<String>

    // Delete a user by ID
    @DELETE("users/{id}")
    fun deleteUserById(@Path("id") userId: Int): Call<ResponseBody>

    @GET("videos/all")
    fun getAllVideos(): Call<List<Video>>
}
