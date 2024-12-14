package com.example.movieapp.data

data class User(
    val id: Int,
    val name: String,
    val email: String,
    val password: String,
    val role: String,
    val loginStatus: Int
)
