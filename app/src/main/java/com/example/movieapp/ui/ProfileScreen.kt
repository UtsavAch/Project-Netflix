package com.example.movieapp.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.movieapp.ui.theme.AppTheme

@Composable
fun ProfileScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(25.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
        ) { Text(
            text = "Profile Screen",
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Button(onClick={
            navigateToLogin(navController)
        }){
            Text(text="Logout")
        }

        Spacer(modifier = Modifier.weight(1f))
        BottomNavigationBar(navController)
    }
}

@Preview
@Composable
fun ProfilePreview(){
    AppTheme {
        ProfileScreen(rememberNavController())
    }
}