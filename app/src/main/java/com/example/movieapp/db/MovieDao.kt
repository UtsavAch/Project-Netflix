package com.example.movieapp.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.movieapp.Movie

@Dao
interface MovieDao {

    @Query("SELECT * FROM Movie ORDER BY createdAt DESC")
    fun getAllMovie() : LiveData<List<Movie>>

    @Insert //Could be manually implemented but androidx.room already provides @Insert for that
    fun addMovie(movie: Movie)

    @Query("Delete FROM Movie where id = :id")
    fun deleteMovie(id: Int)
}
