package com.example.movieapp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.movieapp.R

@Composable
fun BottomIconButton(
    icon: ImageVector,
    contentDescription: String,
    onClick: () -> Unit
) {
    IconButton(onClick = onClick) {
        Icon(
            imageVector = icon,
            contentDescription = contentDescription,
            modifier = Modifier.size(40.dp),
        )
    }
}

@Composable
fun BottomNavigationBar(navController: NavController, modifier: Modifier) {
    Box(
        modifier = modifier.fillMaxWidth() // Make the Box fill the width
    ) {
        // Add the bottom navigation bar and align it to the bottom
        Surface(
            modifier = Modifier
                .fillMaxWidth() // Makes the bar take up the full width
                .align(Alignment.BottomCenter) // Align it to the bottom of the screen
                .background(Color.White.copy(alpha = 0.9f)), // Increased opacity
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .shadow(4.dp, shape = MaterialTheme.shapes.medium) // Shadow on the top
                    .background(Color.White.copy(alpha = 0.9f))
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = androidx.compose.foundation.layout.Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    BottomIconButton(
                        icon = Icons.Filled.Home,
                        contentDescription = stringResource(R.string.home),
                        onClick = { navigateToHome(navController) }
                    )

                    BottomIconButton(
                        icon = Icons.Filled.Person,
                        contentDescription = stringResource(R.string.profile),
                        onClick = { navigateToProfile(navController) }
                    )
                }
            }
        }
    }
}
