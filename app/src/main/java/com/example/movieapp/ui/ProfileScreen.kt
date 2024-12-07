package com.example.movieapp.ui

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.movieapp.R
import com.example.movieapp.ui.theme.AppTheme
import com.example.movieapp.viewmodel.UserViewModel

@Composable
fun ProfileScreen(navController: NavController, modifier: Modifier, viewModel: UserViewModel) {
    var showDialog by remember { mutableStateOf(false) }
    var currentPassword by remember { mutableStateOf("") }
    var newPassword by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
        ) {
        Column(
            modifier = modifier
                .weight(1f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = stringResource(R.string.profile_screen),
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp
            )
            Spacer(modifier = Modifier.height(100.dp))
            Button(
                onClick = { showDialog = true },
                modifier = modifier.padding(16.dp)
            ) {
                Text(text = stringResource(R.string.change_password))
            }

            Button(onClick = {
                navigateToLogin(navController)

            }) {
                Text(text = stringResource(R.string.logout))
            }
        }

        Spacer(modifier = modifier.weight(1f))

        BottomNavigationBar(navController, modifier)

        }

        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = { Text(text = stringResource(R.string.change_password)) },
                text = {
                    Column {
                        OutlinedTextField(
                            value = currentPassword,
                            onValueChange = { currentPassword = it },
                            label = { Text(stringResource(R.string.current_password)) },
                            visualTransformation = PasswordVisualTransformation(),
                            modifier = Modifier.fillMaxWidth()
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        OutlinedTextField(
                            value = newPassword,
                            onValueChange = { newPassword = it },
                            label = { Text(stringResource(R.string.new_password)) },
                            visualTransformation = PasswordVisualTransformation(),
                            modifier = Modifier.fillMaxWidth()
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        OutlinedTextField(
                            value = confirmPassword,
                            onValueChange = { confirmPassword = it },
                            label = { Text(stringResource(R.string.confirm_new_password)) },
                            visualTransformation = PasswordVisualTransformation(),
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                },
                confirmButton = {
                    Button(
                        onClick = {
                            if (newPassword == confirmPassword) {
                                viewModel.updatePassword("email@example.com", newPassword) // currently the passwords do not change need to see what to do in database
                                Toast.makeText(navController.context, "Password Changed Successfully", Toast.LENGTH_SHORT).show()
                                showDialog = false
                            } else {
                                Toast.makeText(navController.context, "Passwords do not match", Toast.LENGTH_SHORT).show()
                            }
                        }
                    ) {
                        Text(stringResource(R.string.change_password))
                    }
                },
                dismissButton = {
                    Button(onClick = { showDialog = false }) {
                        Text(stringResource(R.string.cancel))
                    }
                }
            )
        }

}

@Preview
@Composable
fun ProfilePreview() {
    AppTheme {
        ProfileScreen(rememberNavController(), modifier = Modifier, viewModel())
    }
}
