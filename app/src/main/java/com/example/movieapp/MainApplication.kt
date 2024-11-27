package com.example.movieapp

import android.app.Application
import androidx.room.Room
import com.example.movieapp.db.MovieDatabase

class MainApplication : Application() {
    companion object {
        lateinit var movieDatabase: MovieDatabase
    }

    override fun onCreate() {
        super.onCreate()
        movieDatabase = Room.databaseBuilder(
            applicationContext,
            MovieDatabase::class.java,
            MovieDatabase.NAME
        ).build()
    }
}
