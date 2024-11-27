package com.example.movieapp.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.movieapp.Movie

@Database(entities = [Movie::class], version=1) //We can add many entities, for example users if there are
@TypeConverters(Converters::class)
abstract class MovieDatabase: RoomDatabase() {
    companion object {
        const val NAME = "Movie_DB"
    }

    abstract fun getMovieDao() : MovieDao
}
