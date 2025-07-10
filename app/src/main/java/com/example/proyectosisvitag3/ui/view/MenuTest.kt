package com.example.proyectosisvitag3.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.proyectosisvitag3.ui.theme.SoftMint
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember // Asegúrate de tener este import
import androidx.compose.runtime.rememberUpdatedState
import androidx.lifecycle.viewmodel.compose.viewModel // Para un futuro ViewModel
import com.example.proyectosisvitag3.ui.theme.SoftMint

// Define a simple data class for the test items
data class TestItemData(
    val id: Int,
    val nombreTest: String,
    val autorTest: String,
    val descripcionTest: String,
    val numberOfQuestions: Int // <-- ADD THIS FIELD
)

// Sample data - replace with your actual data source (e.g., ViewModel)
val sampleTestList = listOf(
    TestItemData(1, "Test de Ansiedad Generalizada", "Dr. Beck", "Mide los niveles de ansiedad generalizada en adultos.", 10), // Example number of questions
    TestItemData(2, "Inventario de Depresión", "Aaron T. Beck", "Evalúa la severidad de los síntomas depresivos.", 15),
    TestItemData(3, "Escala de Estrés Percibido", "Cohen, Kamarck & Mermelstein", "Mide el grado en que las situaciones de la vida se perciben como estresantes.", 5),
    TestItemData(4, "Cuestionario de Autoestima", "Rosenberg", "Evalúa la autoestima global de un individuo.", 8)
)


@Composable
fun MenuTestScreen(navController: NavHostController) {
    // Combinamos la lista original con los tests agregados dinámicamente
    // En una app real, esto vendría de un ViewModel que observa una fuente de datos.
    val combinedTestList = remember {
        // Creamos una nueva lista para que Compose detecte el cambio si testsAgregadosGlobal se modifica
        sampleTestList + testsAgregadosGlobal
    }

    // Para forzar la recomposición cuando testsAgregadosGlobal cambia.
    // Esto es un workaround para la simulación. Con ViewModel y StateFlow/LiveData sería más limpio.
    val forceRecompositionKey by rememberUpdatedState(testsAgregadosGlobal.toList())

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = SoftMint // Light cyan background like in the image
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Header
            ScreenHeader( // Using the reusable ScreenHeader
                text = "LISTA DE TEST",
                backgroundColor = Color(0xFFB2EBF2),
                textColor = Color.DarkGray,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Scrollable list of tests
            if (combinedTestList.isEmpty() && forceRecompositionKey.isEmpty()) { // Usamos forceRecompositionKey para re-evaluar
                // Podrías mostrar un mensaje si no hay tests
                Box(modifier = Modifier.weight(1f).fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text("No hay tests disponibles en este momento.")
                }
            } else {
                LazyColumn(
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 20.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(sampleTestList) { testItem ->
                        TestListItem(
                            testData = testItem,
                            navController = navController // Pass navController here
                        )
                    }
                }
            }

            // "Volver al menu" Button
            Button(
                onClick = { navController.popBackStack() },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 40.dp, vertical = 24.dp)
                    .height(50.dp),
                shape = RoundedCornerShape(25.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFFCDD2),
                    contentColor = Color(0xFFB71C1C)
                )
            ) {
                Text(text = "Volver al menu", fontSize = 16.sp, fontWeight = FontWeight.Medium)
            }
        }
    }
}

@Composable
fun TestListItem(
    testData: TestItemData,
    navController: NavHostController // Receive NavHostController
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        // Test Name Button
        Button(
            // ******** THIS IS WHERE THE NAVIGATION LOGIC GOES ********
            onClick = {
                val testId = testData.nombreTest // Use the actual test name from your data
                val numberOfQuestions = testData.numberOfQuestions // Use the actual number from data
                // Ensure there are no spaces or special characters in testId that are problematic for URLs
                // You might need to URL-encode testId if it can contain spaces/special characters
                navController.navigate("cuestionarioScreen/$testId/$numberOfQuestions")
            },
            // ******** END OF NAVIGATION LOGIC ********
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .height(50.dp),
            shape = RoundedCornerShape(25.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF7AF9CB), // Light teal/mint (was A7FFEB)
                contentColor = Color.DarkGray
            )
        ) {
            Text(text = testData.nombreTest, fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Test Details Card
        Card(
            modifier = Modifier
                .fillMaxWidth(0.9f),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFFBF9F1) // Light yellow/cream (was FFF9C4)
            ),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = "Autor: ${testData.autorTest}",
                    fontSize = 14.sp,
                    color = Color.DarkGray,
                    lineHeight = 20.sp
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Descripción: ${testData.descripcionTest}",
                    fontSize = 14.sp,
                    color = Color.DarkGray,
                    lineHeight = 20.sp
                )
            }
        }
    }
}

// Assuming ScreenHeader is defined elsewhere or here
@Composable
fun ScreenHeader(
    text: String,
    modifier: Modifier = Modifier,
    backgroundColor: Color = MaterialTheme.colorScheme.primaryContainer,
    textColor: Color = MaterialTheme.colorScheme.onPrimaryContainer,
    textStyle: androidx.compose.ui.text.TextStyle = MaterialTheme.typography.headlineSmall,
    fontWeight: FontWeight = FontWeight.Bold
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(70.dp)
            .background(backgroundColor),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            style = textStyle,
            fontWeight = fontWeight,
            color = textColor
        )
    }
}


@Preview(showBackground = true, device = "id:pixel_4")
@Composable
fun MenuTestScreenPreview() {
    MenuTestScreen(navController = rememberNavController())
}