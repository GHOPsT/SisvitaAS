package com.example.proyectosisvitag3.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.proyectosisvitag3.ui.theme.ProyectoSisvitaG3Theme // O tu tema
import com.example.proyectosisvitag3.ui.theme.SoftMint
import com.example.proyectosisvitag3.ui.viewmodel.AgregaTestViewModel

// Asumimos que ScreenHeader está definido como en MenuTest.kt o EspecialistaMainScreen.kt
// Si no, puedes copiarlo aquí o importarlo correctamente.

// Definición de TestItemData (asegúrate que esté accesible, podrías moverla a un paquete 'data' o 'model')
// Si ya está en MenuTest.kt y es pública, puedes importarla.
// data class TestItemData(val id: Int, val nombreTest: String, val autorTest: String, val descripcionTest: String, val numberOfQuestions: Int)

// Variable global simulada para almacenar los tests.
// En una aplicación real, esto estaría en un ViewModel compartido, base de datos, etc.
val testsAgregadosGlobal = mutableStateListOf<TestItemData>()


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AgregaTestScreen(
    navController: NavHostController,
    viewModel: AgregaTestViewModel = viewModel()
) {
    var showSuccessDialog by remember { mutableStateOf(false) }
    var showErrorDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "AGREGAR NUEVO TEST",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                        fontWeight = FontWeight.Bold,
                        color = Color.DarkGray
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Volver", tint = Color.DarkGray)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFB2EBF2) // Color del header similar a otras pantallas
                )
            )
        },
        containerColor = SoftMint // Fondo general
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
                .fillMaxSize()
                .verticalScroll(rememberScrollState()), // Para que sea scrollable si el contenido es mucho
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(20.dp))

            OutlinedTextField(
                value = viewModel.nombreTest,
                onValueChange = { viewModel.nombreTest = it },
                label = { Text("Nombre del Test") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
            )
            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = viewModel.autorTest,
                onValueChange = { viewModel.autorTest = it },
                label = { Text("Autor del Test") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
            )
            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = viewModel.descripcionTest,
                onValueChange = { viewModel.descripcionTest = it },
                label = { Text("Descripción Breve") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp), // Para descripción más larga
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
            )
            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = viewModel.numeroPreguntas,
                onValueChange = { viewModel.numeroPreguntas = it.filter { char -> char.isDigit() } }, // Solo números
                label = { Text("Número de Preguntas") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                )
            )

            Spacer(modifier = Modifier.weight(1f)) // Empuja los botones hacia abajo

            Button(
                onClick = {
                    if (viewModel.esFormularioValido()) {
                        val nuevoTest = viewModel.crearTestItemData()
                        if (nuevoTest != null) {
                            // --- SIMULACIÓN DE GUARDADO ---
                            // En una app real: viewModel.guardarTestEnRepositorio(nuevoTest)
                            // Por ahora, lo añadimos a una lista global simulada
                            // y también a la lista de sampleTestList si la estás usando directamente
                            // Esto es SOLO para demostración y que se refleje en MenuTest.kt si usa sampleTestList
                            if (!sampleTestList.any { it.id == nuevoTest.id } && !testsAgregadosGlobal.any{ it.id == nuevoTest.id}) {
                                testsAgregadosGlobal.add(nuevoTest) // Añade a la lista global
                            }
                            // --- FIN SIMULACIÓN ---
                            showSuccessDialog = true
                            viewModel.limpiarCampos()
                        }
                    } else {
                        showErrorDialog = true
                    }
                },
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .height(50.dp),
                shape = RoundedCornerShape(25.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF66D8C8) // Color verde azulado como en EspecialistaMainScreen
                )
            ) {
                Text("GUARDAR TEST", fontSize = 16.sp, fontWeight = FontWeight.Medium)
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = { navController.popBackStack() },
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .height(50.dp),
                shape = RoundedCornerShape(25.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFF4A7A7), // Color rosado como "Cerrar Sesión"
                    contentColor = MaterialTheme.colorScheme.onErrorContainer
                )
            ) {
                Text("CANCELAR", fontSize = 16.sp, fontWeight = FontWeight.Medium)
            }
            Spacer(modifier = Modifier.height(20.dp))
        }
    }

    if (showSuccessDialog) {
        AlertDialog(
            onDismissRequest = { showSuccessDialog = false },
            title = { Text("¡Éxito!") },
            text = { Text("El nuevo test ha sido agregado correctamente.") },
            confirmButton = {
                Button(onClick = {
                    showSuccessDialog = false
                    navController.popBackStack() // Volver a EspecialistaMainScreen
                }) {
                    Text("OK")
                }
            }
        )
    }

    if (showErrorDialog) {
        AlertDialog(
            onDismissRequest = { showErrorDialog = false },
            title = { Text("Error en el Formulario") },
            text = { Text("Por favor, completa todos los campos correctamente. El número de preguntas debe ser mayor a cero.") },
            confirmButton = {
                Button(onClick = { showErrorDialog = false }) {
                    Text("Entendido")
                }
            }
        )
    }
}

@Preview(showBackground = true, device = "id:pixel_4")
@Composable
fun AgregaTestScreenPreview() {
    ProyectoSisvitaG3Theme { // Reemplaza con tu tema si es diferente
        AgregaTestScreen(navController = rememberNavController(), viewModel = AgregaTestViewModel())
    }
}
