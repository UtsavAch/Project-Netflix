package com.example.movieapp

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity
data class Movie (
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var title: String,
    var createdAt: Date
)
