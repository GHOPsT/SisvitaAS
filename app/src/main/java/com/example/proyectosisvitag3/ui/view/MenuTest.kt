package com.example.proyectosisvitag3.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items // Import for LazyColumn items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.proyectosisvitag3.ui.components.ScreenHeader
import com.example.proyectosisvitag3.ui.theme.SoftMint

// Define a simple data class for the test items
data class TestItemData(
    val id: Int,
    val nombreTest: String,
    val autorTest: String,
    val descripcionTest: String
)

// Sample data - replace with your actual data source (e.g., ViewModel)
val sampleTestList = listOf(
    TestItemData(1, "Test de Ansiedad Generalizada", "Dr. Beck", "Mide los niveles de ansiedad generalizada en adultos."),
    TestItemData(2, "Inventario de Depresión", "Aaron T. Beck", "Evalúa la severidad de los síntomas depresivos."),
    TestItemData(3, "Escala de Estrés Percibido", "Cohen, Kamarck & Mermelstein", "Mide el grado en que las situaciones de la vida se perciben como estresantes."),
    //TestItemData(4, "Cuestionario de Autoestima", "Rosenberg", "Evalúa la autoestima global de un individuo.")
)


@Composable
fun MenuTestScreen(navController: NavHostController) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = SoftMint // Light cyan background like in the image
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Header
            ScreenHeader( // Using the reusable ScreenHeader from previous suggestion
                text = "LISTA DE TEST",
                backgroundColor = Color(0xFFB2EBF2), // Slightly darker cyan for header
                textColor = Color.DarkGray, // Adjust as needed
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Scrollable list of tests
            LazyColumn(
                modifier = Modifier
                    .weight(1f) // Takes up available space, pushing "Volver" to bottom
                    .padding(horizontal = 20.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp) // Spacing between test items
            ) {
                items(sampleTestList) { testItem ->
                    TestListItem(
                        testData = testItem,
                        onTestClick = {
                            // TODO: Navigate to the specific test screen, passing testItem.id or name
                            // Example: navController.navigate("takeTestScreen/${testItem.id}")
                        }
                    )
                }
            }

            // "Volver al menu" Button
            Button(
                onClick = { navController.popBackStack() }, // Navigates back to the previous screen
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 40.dp, vertical = 24.dp)
                    .height(50.dp),
                shape = RoundedCornerShape(25.dp), // Pill shape
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFFCDD2), // Light red/pink
                    contentColor = Color(0xFFB71C1C) // Darker red for text
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
    onTestClick: () -> Unit
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        // Test Name Button
        Button(
            onClick = onTestClick,
            modifier = Modifier
                .fillMaxWidth(0.8f) // Button takes 80% of width
                .height(50.dp),
            shape = RoundedCornerShape(25.dp), // Pill shape
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF7AF9CB), // Light teal/mint
                contentColor = Color.DarkGray // Adjust as needed
            )
        ) {
            Text(text = testData.nombreTest, fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Test Details Card
        Card(
            modifier = Modifier
                .fillMaxWidth(0.9f), // Card takes 90% of width
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFFBF9F1) // Light yellow/cream
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

@Preview(showBackground = true, device = "id:pixel_4")
@Composable
fun MenuTestScreenPreview() {
    MenuTestScreen(navController = rememberNavController())
}