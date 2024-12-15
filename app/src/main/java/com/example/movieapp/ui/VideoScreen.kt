package com.example.movieapp.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.example.movieapp.AppViewModel
import com.example.movieapp.data.Video
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.util.EventLogger

@Composable
fun VideoScreen(video: Video, navController: NavController, viewModel: AppViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Display video metadata
        Text(text = video.name)
        Text(text = "Genre: ${video.genre}")

        Spacer(modifier = Modifier.height(16.dp))

        // Pass the master.m3u8 link to the VideoPlayer
        VideoPlayer(hlsUrl = video.link1080p)
    }
}

@Composable
fun VideoPlayer(hlsUrl: String) {
    // Context for ExoPlayer initialization
    val context = LocalContext.current

    // Create and remember ExoPlayer instance
    val exoPlayer = remember {
        ExoPlayer.Builder(context)
            .build()
            .apply {
                // Create MediaItem from the master.m3u8 URL
                val mediaItem = MediaItem.fromUri(hlsUrl)
                setMediaItem(mediaItem)
                prepare()
                addAnalyticsListener(EventLogger())
            }
    }

    // Ensure the ExoPlayer is properly released when the Composable is removed
    DisposableEffect(exoPlayer) {
        onDispose {
            exoPlayer.release()
        }
    }

    // Create and bind PlayerView to ExoPlayer using AndroidView
    AndroidView(
        factory = {
            PlayerView(it).apply {
                player = exoPlayer // Attach ExoPlayer to PlayerView
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(16 / 9f) // Use 16:9 aspect ratio for video player
    )
}

