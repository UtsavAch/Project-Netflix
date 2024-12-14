package com.example.movieapp.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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

@Composable
fun VideoScreen(video: Video, navController: NavController, viewModel: AppViewModel) {
    // Exibir o nome, gênero, etc., do vídeo
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(text = video.name)
        Text(text = "Genre: ${video.genre}")

        // Usando o ExoPlayer para exibir o vídeo
        AndroidView(factory = { context ->
            // Criando o PlayerView de forma moderna
            val playerView = PlayerView(context)

            // Inicializando o ExoPlayer
            val exoPlayer = ExoPlayer.Builder(context).build()

            // Criando o MediaItem para o vídeo
            val mediaItem = MediaItem.fromUri(video.link1080p) // link para o vídeo 1080p

            // Configurando o ExoPlayer
            exoPlayer.setMediaItem(mediaItem)
            exoPlayer.prepare()
            exoPlayer.play()

            // Atribuindo o ExoPlayer ao PlayerView
            playerView.player = exoPlayer

            // Retornando o PlayerView para ser usado no Composable
            playerView
        })
    }
}


