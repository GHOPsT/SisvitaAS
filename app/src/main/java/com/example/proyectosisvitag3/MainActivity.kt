package com.example.proyectosisvitag3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.proyectosisvitag3.ui.theme.ProyectoSisvitaG3Theme
import com.example.proyectosisvitag3.ui.theme.iu.LoginScreen
import com.example.proyectosisvitag3.ui.theme.iu.LoginViewModel
import com.example.proyectosisvitag3.ui.view.RegisterScreen
import com.example.proyectosisvitag3.ui.view.main.MainScreen
import com.example.proyectosisvitag3.ui.viewmodel.RegisterViewModel

// In your MainActivity.kt or where you set up your NavHost

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProyectoSisvitaG3Theme { // Your app's theme
                AppNavigation() // Your top-level navigation composable
            }
        }
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val loginViewModel = remember { LoginViewModel() }
    val registerViewModel = remember { RegisterViewModel() }

    NavHost(navController = navController, startDestination = "mainScreen") { // Set your start destination
        composable("mainScreen") {
            MainScreen(navController = navController)
        }
        composable("loginScreen") {
            // Pass the appropriate ViewModel here
            LoginScreen(navController = navController, viewModel = loginViewModel)
        }
        composable("registerScreen") {
            RegisterScreen(navController = navController, viewModel = registerViewModel)
        }
        composable("studentMainScreen") {
            // Add your StudentMainScreen composable here
            // This is the missing destination!
            // StudentMainScreen(...)
        }
        // Add other destinations here
    }
}