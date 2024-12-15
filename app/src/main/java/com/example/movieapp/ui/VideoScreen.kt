package com.example.movieapp.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.example.movieapp.AppViewModel
import com.example.movieapp.data.Video
import com.google.android.exoplayer2.ExoPlaybackException
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.Format
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.analytics.AnalyticsListener
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.util.EventLogger
import java.io.IOException
import com.google.android.exoplayer2.util.Log;

@Composable
fun VideoScreen(video: Video, navController: NavController, viewModel: AppViewModel) {
    val context = LocalContext.current
    val masterUrl by viewModel.masterUrl.collectAsState()

    // Trigger fetching the master URL when the screen is displayed
    LaunchedEffect(video.id) {
        viewModel.fetchMasterUrl(video.id)
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "Video ID: ${video.id}")
        Text(text = "Streaming Video...")

        Spacer(modifier = Modifier.height(16.dp))

        // Only show VideoPlayer when the signed URL is ready
        masterUrl?.let { VideoPlayer(hlsUrl = it) }
    }
}

@Composable
fun VideoPlayer(hlsUrl: String) {
    // Context for ExoPlayer initialization
    val context = LocalContext.current
    Log.d("VideoPlayer", "HLS URL: $hlsUrl")
    Log.setLogLevel(Log.LOG_LEVEL_ALL);  // Set log level to VERBOSE

    // Create and remember ExoPlayer instance
    val exoPlayer = remember {
        ExoPlayer.Builder(context)
            .build()
            .apply {
                val mediaItem = MediaItem.fromUri(hlsUrl)
                setMediaItem(mediaItem)
                prepare()
                addAnalyticsListener(EventLogger())
                playWhenReady = true // Auto-play video when ready
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
        factory = { context ->
            PlayerView(context).apply {
                player = exoPlayer // Attach ExoPlayer to PlayerView
                useController = true // Show player controls
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(16 / 9f) // Use 16:9 aspect ratio for video player
    )
}

