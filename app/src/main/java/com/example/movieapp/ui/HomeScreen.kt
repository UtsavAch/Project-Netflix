package com.example.movieapp.ui

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.movieapp.AppViewModel
import com.example.movieapp.R
import com.example.movieapp.ui.theme.AppTheme
import com.example.movieapp.data.Video

@Composable
fun HomeScreen(navController: NavController, viewModel: AppViewModel) {
    // Intercepts going back behavior
    BackHandler {
        Log.d("HomeScreen", "Button going back HomeScreen.")
    }

    var searchQuery by remember { mutableStateOf("") }

    // Collect video list from ViewModel
    val videoList by viewModel.videoList.collectAsState(initial = emptyList())

    // Filter videos based on the search query
    val filteredVideos = videoList.filter {
        it.name.contains(searchQuery, ignoreCase = true)
    }

    // Group videos by genre
    val videosByGenre = filteredVideos.groupBy { it.genre }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(25.dp)
            .verticalScroll(rememberScrollState()), // Added vertical scroll
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Search bar at the top
        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            label = { Text(text = "Search for a video") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Display videos by genre
        if (videoList.isEmpty()) {
            Text(text = "Loading videos...", style = MaterialTheme.typography.bodyMedium)
        } else {
            videosByGenre.forEach { (genre, videos) ->
                Text(
                    text = genre,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.bodyLarge, // Updated text style
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                // Horizontal scrollable row for videos of a specific genre
                Row(
                    modifier = Modifier
                        .horizontalScroll(rememberScrollState())
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    videos.forEach { video ->
                        VideoCard(video = video, onClick = {
                            // Navigate to detailed screen (simulated)
                            Log.d("HomeScreen", "Navigating to details of: ${video.name}")
                        })
                    }
                }

                Spacer(modifier = Modifier.height(20.dp)) // Add space between genre sections
            }
        }

        Spacer(modifier = Modifier.weight(1f))
        BottomNavigationBar(navController, modifier = Modifier)
    }
}



@Composable
fun VideoCard(video: Video, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .width(150.dp)
            .height(250.dp)
            .clickable { onClick() },
        colors = CardDefaults.cardColors(containerColor = Color.LightGray)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = video.name,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(text = "Genre: ${video.genre}")
            Text(text = "Duration: ${video.duration / 60}h ${video.duration % 60}m")

            Spacer(modifier = Modifier.height(16.dp))

            // Change Row to Column to stack buttons vertically
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp) // Space between buttons
            ) {
                Button(onClick = {
                    Log.d("VideoCard", "Watching video ${video.name} at 1080p: ${video.link1080p}")
                    // Add logic to open the video in 1080p
                }) {
                    Text(text = "1080p")
                }

                Button(onClick = {
                    Log.d("VideoCard", "Watching video ${video.name} at 360p: ${video.link360p}")
                    // Add logic to open the video in 360p
                }) {
                    Text(text = "360p")
                }
            }
        }
    }
}

/*
@Preview
@Composable
fun HomePreview() {
    AppTheme {
        HomeScreen(rememberNavController())
    }
}*/