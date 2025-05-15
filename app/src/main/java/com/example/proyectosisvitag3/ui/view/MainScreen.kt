// ui/view/main/MainScreen.kt
package com.example.proyectosisvitag3.ui.view.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.proyectosisvitag3.R
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController

val luckiestGuy = FontFamily(
    Font(R.font.luckiest_guy)
)

@Composable
fun MainScreen(navController: NavHostController) { // Considera pasar lambdas de navegación aquí
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFFEFFFFF)
    ) {
        MainScreenContent(
            onStudentClick = { navController.navigate("loginScreen") }, // Pasar acciones de navegación
            onSpecialistClick = { navController.navigate("loginScreen") },
            onVirtualAssistantClick = { navController.navigate("loginScreen") }
        )
    }
}

@Composable
fun MainScreenContent(
    onStudentClick: () -> Unit,
    onSpecialistClick: () -> Unit,
    onVirtualAssistantClick: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        MainScreenLogo() // Componente más pequeño
        MainScreenTitle() // Componente más pequeño
        Spacer(modifier = Modifier.height(30.dp))
        UserTypeButtons( // Componente que agrupa los botones
            onStudentClick = onStudentClick,
            onSpecialistClick = onSpecialistClick,
            onVirtualAssistantClick = onVirtualAssistantClick
        )
    }
}

@Composable
fun MainScreenLogo() {
    Image(
        painter = painterResource(id = R.drawable.logo_sisvita),
        contentDescription = "Logo Sisvita",
        modifier = Modifier
            .size(240.dp)
            .padding(bottom = 16.dp)
    )
}

@Composable
fun MainScreenTitle() {
    Text(
        text = "Bienvenido",
        style = TextStyle(fontSize = 45.sp, color = Color.Black,  fontFamily = luckiestGuy)
    )
}

@Composable
fun UserTypeButtons(
    onStudentClick: () -> Unit,
    onSpecialistClick: () -> Unit,
    onVirtualAssistantClick: () -> Unit
) {
    Column {
        Button(
            onClick = onStudentClick,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 80.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF27C8C8))
        ) {
            Text(text = "ESTUDIANTE")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = onSpecialistClick,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 80.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF27C8C8))
        ) {
            Text(text = "ESPECIALISTA")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = onVirtualAssistantClick,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 80.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF27C8C8))
        ) {
            Text(text = "ASISTENTE VIRTUAL")
        }
    }
}

// Mover estas funciones a un archivo de componentes si se reutilizan
@Composable
fun AlumnoImage(modifier: Modifier) {
    Image(
        painter = painterResource(id = R.drawable.estudiantes),
        contentDescription = "Header",
        modifier = modifier
    )
}

@Composable
fun ProfesionalImage(modifier: Modifier) {
    Image(
        painter = painterResource(id = R.drawable.profesional),
        contentDescription = "Header",
        modifier = modifier
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MainScreenPreview() {
    val navController = rememberNavController()
    MainScreen(navController = navController)
}