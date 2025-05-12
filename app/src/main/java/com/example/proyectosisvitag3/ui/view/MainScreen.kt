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
import androidx.compose.ui.text.font.Font
import androidx.compose.foundation.border
import androidx.compose.foundation.shape.RoundedCornerShape


val luckiestGuy = FontFamily(
    Font(R.font.luckiest_guy)
)

@Composable
fun MainScreen(navController: NavHostController) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFFEFFFFF)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo_sisvita),
                contentDescription = "Logo Sisvita",
                modifier = Modifier
                    .size(240.dp)
                    .padding(bottom = 16.dp)
            )

            Text(
                text = "Bienvenido",
                style = TextStyle(fontSize = 45.sp, color = Color.Black,  fontFamily = luckiestGuy)
            )
            Spacer(modifier = Modifier.height(30.dp))
            Column {
                Button(
                    onClick = { navController.navigate("loginScreen") }, //Aquí debería ir la pantalla para login de estudiante
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 80.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF27C8C8))
                ) {
                    Text(text = "ESTUDIANTE")
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Column {
                Button(
                    onClick = { navController.navigate("loginScreen") }, // Aquí debería ir la navegación a la pantalla de login para profesionales
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 80.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF27C8C8))
                ) {
                    Text(text = "ESPECIALISTA")
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Column {
                Button(
                    onClick = { navController.navigate("loginScreen") }, // Aquí debería ir a donde sea que te mande ese botón xd
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 80.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF27C8C8))
                ) {
                    Text(text = "ASISTENTE VIRTUAL")
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
