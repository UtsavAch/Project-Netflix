package com.example.movieapp.ui

import android.app.Activity
import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.movieapp.AppViewModel
import com.example.movieapp.R
import com.example.movieapp.data.Video
import com.example.movieapp.ui.theme.AppTheme
import androidx.compose.foundation.lazy.items

@Composable
fun VideoItem(video: Video, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable(onClick = onClick)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = video.name, style = MaterialTheme.typography.titleMedium)
            Text(text = "Genre: ${video.genre}", style = MaterialTheme.typography.labelMedium)
        }
    }
}


@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: AppViewModel
) {
    val videos by viewModel.videoList.collectAsState()

    // inteceps ging back behaviour
    BackHandler {
        // Suspends nav
        Log.d("HomeScreen", "Button going back HomeScreen.")
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(25.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
        ){

        item {
            Text(
                text = stringResource(R.string.home_screen),
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp),
                style = MaterialTheme.typography.displayLarge
            )
        }

        items(videos) { video ->
            VideoItem(
                video = video,
                onClick = {
                    navigateToVideo(navController, video.id)
                }
            )
        }


        item{
            Column{
                Spacer(modifier = Modifier.weight(1f))
                BottomNavigationBar(navController, modifier = Modifier)
            }
        }

    }
}


@Preview
@Composable
fun HomePreview(){
    AppTheme {
        HomeScreen(rememberNavController(), viewModel())
    }
}