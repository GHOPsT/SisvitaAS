package com.example.proyectosisvitag3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.proyectosisvitag3.ui.theme.ProyectoSisvitaG3Theme
import com.example.proyectosisvitag3.ui.theme.iu.*
import com.example.proyectosisvitag3.ui.theme.iu.formulario.PreguntasCuestionario
import com.example.proyectosisvitag3.ui.view.main.MainScreen

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

    NavHost(navController = navController, startDestination = "mainScreen") { // Set your start destination
        composable("mainScreen") {
            MainScreen(navController = navController)
        }
        composable("loginScreen") {
            // Pass the appropriate ViewModel here
            LoginScreen(navController = navController, viewModel = loginViewModel)
        }
        composable("registerScreen") {
            // Add your RegisterScreen composable and its ViewModel
        }
        composable("studentMainScreen") {
            // Add your StudentMainScreen composable here
            // This is the missing destination!
            // StudentMainScreen(...)
        }
        // Add other destinations here
    }
}