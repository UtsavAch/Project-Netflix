package com.example.movieapp

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.Instant
import java.util.Date

class MovieViewModel : ViewModel(){
    val movieDao = MainApplication.movieDatabase.getMovieDao()


    val movieList : LiveData<List<Movie>> = movieDao.getAllMovie()


    @RequiresApi(Build.VERSION_CODES.O)
    fun addMovie(title : String){
        viewModelScope.launch(Dispatchers.IO) {
            movieDao.addMovie(Movie(title = title, createdAt = Date.from(Instant.now())))
        }
    }

    fun deleteMovie(id : Int){
        viewModelScope.launch(Dispatchers.IO) {
            movieDao.deleteMovie(id)
        }
    }
}