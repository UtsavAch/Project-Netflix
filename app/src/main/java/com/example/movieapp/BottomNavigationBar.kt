package com.example.movieapp

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.vector.ImageVector

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
            tint = Color.Blue
        )
    }
}

@Composable
fun BottomNavigationBar() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = androidx.compose.foundation.layout.Arrangement.SpaceEvenly
    ) {
        BottomIconButton(
            icon = Icons.Filled.Home,
            contentDescription = "Home",
            onClick = { /* Navigate to Home- It will be default */ }
        )

        BottomIconButton(
            icon = Icons.Filled.Star,
            contentDescription = "Trending",
            onClick = { /* Navigate to Trending section */ }
        )

        BottomIconButton(
            icon = Icons.Filled.Person,
            contentDescription = "Profile",
            onClick = { /* Navigate to Profile */ }
        )
    }
}
