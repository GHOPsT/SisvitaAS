package com.example.proyectosisvitag3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.proyectosisvitag3.ui.theme.ProyectoSisvitaG3Theme
import com.example.proyectosisvitag3.ui.theme.iu.*
import com.example.proyectosisvitag3.ui.theme.iu.formulario.PreguntasCuestionario

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProyectoSisvitaG3Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(0xFFEFFFFF) // Tu color de fondo deseado
                ) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "mainScreen") {
                        composable("mainScreen") { MainScreen(navController) }
                        composable("loginScreen") { LoginScreen(navController, LoginViewModel()) }
                        composable("studentMainScreen") { StudentMainScreen(navController) }
                        composable("EspecialistaMainScreen") { EspecialistaMainScreen() }
                        composable("evaluarResultadoScreen") { EvaluarResultadoScreen(navController, EvaluarResultadoViewModel()) }
                        composable("termsAndConditionsScreen") { TermsAndConditionsScreen(navController) }
                        composable("testScreen") { PreguntasCuestionario(CantPreguntas = 10, CantRespuestas = 4) }
                    }
                }
            }
        }
    }
}
