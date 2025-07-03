package com.example.proyectosisvitag3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.proyectosisvitag3.ui.theme.ProyectoSisvitaG3Theme
import com.example.proyectosisvitag3.ui.theme.iu.LoginScreen
import com.example.proyectosisvitag3.ui.theme.iu.LoginViewModel
import com.example.proyectosisvitag3.ui.view.ChatScreen
import com.example.proyectosisvitag3.ui.view.CuestionarioScreen
import com.example.proyectosisvitag3.ui.view.EspecialistaMainScreen
import com.example.proyectosisvitag3.ui.view.EspecialistaMainViewModel
import com.example.proyectosisvitag3.ui.view.StudentMainScreen
import com.example.proyectosisvitag3.ui.view.StudentMainViewModel
import com.example.proyectosisvitag3.ui.view.MenuTestScreen
import com.example.proyectosisvitag3.ui.view.RegisterScreen
import com.example.proyectosisvitag3.ui.view.VirtualAssistantScreen
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
    val studentMainViewModel: StudentMainViewModel = viewModel() // ViewModel for StudentMainScreen

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
            StudentMainScreen(navController = navController, viewModel = studentMainViewModel)
        }
        composable("menuTestScreen") {
            MenuTestScreen(navController = navController)
            // If MenuTestScreen also needs a ViewModel, provide it here
        }
        composable("especialistaMainScreen") {
            val especialistaViewModel: EspecialistaMainViewModel = viewModel()
            EspecialistaMainScreen(navController = navController, viewModel = especialistaViewModel)
        }

        composable("virtualAssistantScreen") {
            VirtualAssistantScreen(navController = navController)
        }

        composable("chatScreen") {
            ChatScreen(navController = navController,)
        }

        composable(
            route = "cuestionarioScreen/{nombreTest}/{cantPreguntas}",
            arguments = listOf(
                navArgument("nombreTest") { type = NavType.StringType },
                navArgument("cantPreguntas") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val testName = backStackEntry.arguments?.getString("nombreTest") ?: "Test Desconocido"
            val numQuestions = backStackEntry.arguments?.getInt("cantPreguntas") ?: 0
            CuestionarioScreen(
                navController = navController,
                nombreTest = testName,
                cantPreguntas = numQuestions
            )
        }
        // Add other destinations here
    }
}