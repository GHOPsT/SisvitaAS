package com.example.proyectosisvitag3.ui.view // Or your correct package

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.proyectosisvitag3.ui.components.ScreenHeader // Assuming ScreenHeader is in this package
import com.example.proyectosisvitag3.ui.theme.SoftMint // Your defined background color

// --- Data Class for Completed Test ---
data class CompletedTestData(
    val id: Int,
    val testName: String,
    val userName: String,
    val score: Int,
    val anxietyLevel: String, // e.g., "Leve", "Moderado", "Alto"
    val interpretation: String // Detailed interpretation
)

// --- Sample Data (Replace with your actual data source, e.g., ViewModel) ---
val sampleCompletedTestList = listOf(
    CompletedTestData(1, "Ansiedad Generalizada", "Usuario Ejemplo 1", 15, "Moderado", "Presenta síntomas moderados de ansiedad."),
    CompletedTestData(2, "Depresión de Beck", "Usuario Ejemplo 2", 22, "Alto", "Sugiere una evaluación más profunda por síntomas depresivos altos."),
    CompletedTestData(3, "Estrés Percibido", "Usuario Ejemplo 3", 8, "Leve", "Niveles de estrés percibido dentro de rangos bajos.")
)

// --- Composable Screen ---
@Composable
fun ListaTestRealizadosScreen(navController: NavHostController) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = SoftMint // Light cyan background from the image
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // --- Header: "LISTA DE TEST REALIZADOS" ---
            ScreenHeader(
                text = "LISTA DE TEST REALIZADOS",
                backgroundColor = Color(0xFFB2EBF2), // Light Blue-ish Gray, similar to MenuTest
                textColor = Color.DarkGray
            )

            // --- Scrollable List of Completed Tests ---
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 16.dp, vertical = 20.dp)
                    // The image shows an inner container with a different background
                    .background(Color(0xFFE0F7FA), shape = RoundedCornerShape(20.dp)) // Very light cyan/blue inner background
                    .padding(16.dp), // Padding inside the inner container
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(sampleCompletedTestList) { completedTest ->
                    CompletedTestItem(
                        testData = completedTest,
                        onEvaluateClick = {
                            // TODO: Navigate to evaluation screen or show evaluation details
                            // navController.navigate("evaluarTestScreen/${completedTest.id}")
                        }
                    )
                }
            }

            // --- "Volver a Menú" Button ---
            Button(
                onClick = {
                    navController.popBackStack() // Or navigate to a specific menu screen if needed
                },
                modifier = Modifier
                    .fillMaxWidth(0.7f) // Button doesn't span full width
                    .padding(vertical = 24.dp)
                    .height(50.dp),
                shape = RoundedCornerShape(25.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFB3E5FC), // Light blue color from the image for this button
                    contentColor = Color.DarkGray
                )
            ) {
                Text(text = "Volver a Menú", fontSize = 16.sp, fontWeight = FontWeight.Medium)
            }
        }
    }
}

@Composable
fun CompletedTestItem(
    testData: CompletedTestData,
    onEvaluateClick: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp), // Rounded corners for the card
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFFCE4EC) // Light pinkish background for the card from the image
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally // Center the button
        ) {
            Text(
                text = "TEST DE \"${testData.testName}\"",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = Color.DarkGray,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(12.dp))

            // Using Text with bullet points (simple approach)
            Text(
                text = "• NOMBRE DE USUARIO: ${testData.userName}\n" +
                        "• Puntaje: ${testData.score}\n" +
                        "• Nivel de Ansiedad: \"${testData.anxietyLevel}\"",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.DarkGray,
                lineHeight = 20.sp, // Adjust line height for readability
                modifier = Modifier.fillMaxWidth() // Align text to the start
            )
            // If interpretation is long, you might want a separate Text or a "show more"
            Text(
                text = "\"${testData.interpretation}\"",
                style = MaterialTheme.typography.bodySmall,
                color = Color.DarkGray.copy(alpha = 0.8f), // Slightly lighter for interpretation
                lineHeight = 18.sp,
                modifier = Modifier.fillMaxWidth().padding(top = 4.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // --- "Evaluar test" Button (Inside the Card) ---
            Button(
                onClick = onEvaluateClick,
                modifier = Modifier
                    .fillMaxWidth(0.8f) // Button doesn't span full width of the card
                    .height(48.dp),
                shape = RoundedCornerShape(24.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFB2DFDB), // Light teal/cyan color from the image for this button
                    contentColor = Color.DarkGray
                )
            ) {
                Text(text = "Evaluar test", fontSize = 15.sp, fontWeight = FontWeight.Medium)
            }
        }
    }
}

@Preview(showBackground = true, device = "id:pixel_4")
@Composable
fun ListaTestRealizadosScreenPreview() {
    // ProyectoSisvitaG3Theme { // Apply your app's theme if you have one
    ListaTestRealizadosScreen(navController = rememberNavController())
    // }
}