package com.example.movieapp.data

data class User(
    val id: Int = 0,
    val name: String,
    val email: String,
    val password: String,
    val role: String = "user",
    val login_status: Int = 0
)
