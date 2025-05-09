package com.example.proyectosisvitag3.ui.theme.iu

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.proyectosisvitag3.R
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController

@Composable
fun MainScreen(navController: NavHostController) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Bienvenido",
                style = TextStyle(fontSize = 45.sp, color = Color.Black, fontFamily = FontFamily.Cursive)
            )
            Spacer(modifier = Modifier.height(30.dp))
            Column {
                AlumnoImage(
                    Modifier
                        .align(Alignment.CenterHorizontally)
                        .size(200.dp)
                )
                Button(
                    onClick = { navController.navigate("loginScreen") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 80.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF3E03))
                ) {
                    Text(text = "Iniciar como Alumno")
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Column {
                ProfesionalImage(
                    Modifier
                        .align(Alignment.CenterHorizontally)
                        .size(200.dp)
                )
                Button(
                    onClick = { navController.navigate("loginScreen") }, // Aquí debería ir la navegación a la pantalla de login para profesionales
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 80.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF4303))
                ) {
                    Text(text = "Iniciar como Profesional")
                }
            }
        }
    }
}

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
