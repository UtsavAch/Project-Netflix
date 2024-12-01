package com.example.movieapp.ui

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
import androidx.compose.ui.res.stringResource
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
            tint = Color.Blue
        )
    }
}

@Composable
fun BottomNavigationBar(navController: NavController) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = androidx.compose.foundation.layout.Arrangement.SpaceEvenly
    ) {
        BottomIconButton(
            icon = Icons.Filled.Home,
            contentDescription = stringResource(R.string.home),
            onClick = { navigateToHome(navController) }
        )

        BottomIconButton(
            icon = Icons.Filled.Star,
            contentDescription = stringResource(R.string.trending),
            onClick = { navigateToTrending(navController) }
        )

        BottomIconButton(
            icon = Icons.Filled.Person,
            contentDescription = stringResource(R.string.profile),
            onClick = { navigateToProfile(navController) }
        )
    }
}
