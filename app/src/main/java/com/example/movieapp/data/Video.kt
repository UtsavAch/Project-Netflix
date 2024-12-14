package com.example.movieapp.data

data class Video(
    val id: Int,
    val name: String,
    val genre: String,
    val duration: Int,
    val link1080p: String,
    val link360p: String
)