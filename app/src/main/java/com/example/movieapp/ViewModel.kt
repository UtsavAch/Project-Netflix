package com.example.movieapp

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.api.ApiService
import com.example.movieapp.api.RetrofitInstance
import com.example.movieapp.data.Video
import com.example.movieapp.data.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AppViewModel : ViewModel() {

    private val _userList = MutableStateFlow<List<User>>(emptyList())
    private val _videoList = MutableStateFlow<List<Video>>(emptyList())

    val userList: StateFlow<List<User>> = _userList
    val videoList: StateFlow<List<Video>> = _videoList

    // Simulate a backend data source
    //private val users = mutableListOf<User>()
    private val videos = mutableListOf<Video>()

    // Unique ID generators
    private var nextUserId = 3
    private var nextVideoId = 3

    init {
        loadUsers()
        loadVideos()
    }

    // Fetch user by ID
    fun fetchUserById(userId: String, onResult: (User?) -> Unit) {
        val apiService = RetrofitInstance.retrofit.create(ApiService::class.java)
        val call = apiService.getUser(userId)
        call.enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.isSuccessful) {
                    val user = response.body()
                    onResult(user)
                } else {
                    onResult(null)
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                println("Error: ${t.message}")
                onResult(null)
            }
        })
    }

    private fun loadUsers() {
        val apiService = RetrofitInstance.retrofit.create(ApiService::class.java)

        apiService.getAllUsers().enqueue(object : Callback<List<User>> {
            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                if (response.isSuccessful && response.body() != null) {
                    val fetchedUsers = response.body()!! // not null type
                    Log.d("AppViewModel", "Fetched Users: $fetchedUsers")
                    _userList.value = fetchedUsers
                } else {
                    Log.e("AppViewModel", "Failed to load users: ${response.errorBody()?.string()}")
                }
            }

            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                Log.e("AppViewModel", "Error fetching users: ${t.message}")
            }
        })
    }


    private fun loadVideos() {
        val apiService = RetrofitInstance.retrofit.create(ApiService::class.java)

        apiService.getAllVideos().enqueue(object : Callback<List<Video>> {
            override fun onResponse(call: Call<List<Video>>, response: Response<List<Video>>) {
                if (response.isSuccessful && response.body() != null) {
                    val fetchedVideos = response.body()!! // not null type
                    Log.d("AppViewModel", "Fetched Videos: $fetchedVideos")
                    _videoList.value = fetchedVideos
                } else {
                    Log.e("AppViewModel", "Failed to load videos: ${response.errorBody()?.string()}")
                }
            }

            override fun onFailure(call: Call<List<Video>>, t: Throwable) {
                Log.e("AppViewModel", "Error fetching videos: ${t.message}")
            }
        })
    }

    fun addUser(name: String, email: String, password: String, onSuccess: () -> Unit, onFailure: (String) -> Unit) {
        val apiService = RetrofitInstance.retrofit.create(ApiService::class.java)
        val newUser = User(0, name, email, password, "user", 0)

        apiService.addUser(newUser).enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if (response.isSuccessful && response.body() != null) {
                    val message = response.body()!! // not null type
                    Log.d("AppViewModel", "Message: $message")
                    loadUsers()
                    onSuccess()
                } else {
                    Log.e("AppViewModel", "Failed add user: ${response.errorBody()?.string()}")
                    onFailure("Failed to add user")
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.e("AppViewModel", "Error adding user: ${t.message}")
            }
        })
    }


    fun removeUser(user: User) {
        val apiService = RetrofitInstance.retrofit.create(ApiService::class.java)

        apiService.deleteUserById(user.id).enqueue(object : Callback<ResponseBody>{
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful && response.body() != null) {
                    val message = response.body()!! // not null type
                    Log.d("AppViewModel", "Deleted: $message")
                    _userList.value = _userList.value.filter { it.id != user.id }
                    //loadUsers()
                } else {
                    Log.e("AppViewModel", "Failed to delete user: ${response.errorBody()?.string()}")
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.e("AppViewModel", "Error deleting user: ${t.message}")
            }
        })
    }

    fun addVideo(name: String, genre: String) {
        viewModelScope.launch {
            val newVideo = Video(nextVideoId++, name, genre, "null", "null")
            videos.add(newVideo)
            _videoList.value = videos
        }
    }

    fun removeVideo(video: Video) {
        viewModelScope.launch {
            videos.remove(video)
            _videoList.value = videos
            Log.d("AppViewModel", "Movies after deletion: ${_videoList.value}") // Log to see the updated list
        }
    }
}