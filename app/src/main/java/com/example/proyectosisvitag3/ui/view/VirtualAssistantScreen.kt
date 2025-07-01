package com.example.proyectosisvitag3.ui.view

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.proyectosisvitag3.ui.view.main.MainScreenLogo

@Composable
fun VirtualAssistantScreen(navController: NavHostController) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFFEFFFFF) // Mismo color de fondo que MainScreen
    ) {
        VirtualAssistantScreenContent(
            onStartClick = {
                // Aquí puedes definir la navegación para '¡Empecemos!'
                // Por ejemplo: navController.navigate("otraRuta")
            },
            onBackClick = {
                navController.popBackStack() // Regresa a la pantalla anterior (MainScreen)
            }
        )
    }
}

@Composable
fun VirtualAssistantScreenContent(
    onStartClick: () -> Unit,
    onBackClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp), // Agregamos un padding general
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Reutilizamos el logo de MainScreen
        MainScreenLogo()
        Spacer(modifier = Modifier.height(60.dp)) // Aumentamos el espacio
        VirtualAssistantButtons(
            onStartClick = onStartClick,
            onBackClick = onBackClick
        )
    }
}

@Composable
fun VirtualAssistantButtons(
    onStartClick: () -> Unit,
    onBackClick: () -> Unit
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) { // Centramos los botones
        Button(
            onClick = onStartClick,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 60.dp), // Ajustamos padding si es necesario
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF27C8C8)) // Mismo color de botón
        ) {
            Text(text = "¡EMPECEMOS!")
        }
        Spacer(modifier = Modifier.height(20.dp)) // Aumentamos espacio entre botones
        Button(
            onClick = onBackClick,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 60.dp), // Ajustamos padding si es necesario
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFA726)) // Un color diferente para "Regresemos"
        ) {
            Text(text = "REGRESEMOS")
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun VirtualAssistantScreenPreview() {
    val navController = rememberNavController()
    VirtualAssistantScreen(navController = navController)
}