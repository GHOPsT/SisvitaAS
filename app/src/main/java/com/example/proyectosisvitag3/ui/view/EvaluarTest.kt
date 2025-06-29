package com.example.proyectosisvitag3.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.proyectosisvitag3.ui.components.ScreenHeader
import com.example.proyectosisvitag3.ui.theme.SoftMint

// --- Data Classes (Simplified for example) ---
data class PreguntaRespuesta(
    val pregunta: String,
    val respuestaEstudiante: String
)

data class TestEvaluationDetails(
    val testId: Int,
    val testName: String,
    val userName: String,
    val puntajeObtenido: Int,
    val respuestas: List<PreguntaRespuesta>,
    val nivelesAnsiedadDisponibles: List<String> // e.g., ["Leve", "Moderado", "Alto", "Muy Alto"]
)

// --- Sample Data (Replace with ViewModel and actual data fetching) ---
val sampleEvaluationData = TestEvaluationDetails(
    testId = 1,
    testName = "Ansiedad Generalizada (GAD-7)",
    userName = "Usuario Ejemplo",
    puntajeObtenido = 15,
    respuestas = listOf(
        PreguntaRespuesta("Pregunta 1: ¿Se ha sentido nervioso/a, ansioso/a o con los nervios de punta?", "Moderadamente"),
        PreguntaRespuesta("Pregunta 2: ¿No ha podido parar o controlar sus preocupaciones?", "Varias veces"),
        PreguntaRespuesta("Pregunta 3: ¿Se ha preocupado demasiado por diferentes cosas?", "Casi todos los días")
        // ... add more questions and answers
    ),
    nivelesAnsiedadDisponibles = listOf("Sin Ansiedad", "Ansiedad Leve", "Ansiedad Moderada", "Ansiedad Severa")
)

// --- Composable Screen ---
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EvaluarTestScreen(
    navController: NavHostController,
    testId: Int // Assume this is passed via navigation to fetch the correct test
    // You would typically use a ViewModel here to fetch and manage data
) {
    // In a real app, fetch data based on testId using a ViewModel
    val evaluationData = sampleEvaluationData // Using sample data for now
    var tratamientoText by remember { mutableStateOf(TextFieldValue("")) }
    var selectedNivelAnsiedad by remember { mutableStateOf<String?>(null) }
    var showNivelAnsiedadDropdown by remember { mutableStateOf(false) }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = SoftMint // Light cyan background from the image
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ScreenHeader(
                text = "TEST DE ${evaluationData.userName.uppercase()}", // Uppercase to match image style
                backgroundColor = Color(0xFFB2EBF2), // Light Blue-ish Gray, similar to MenuReviewTest
                textColor = Color.DarkGray
            )

            // --- Scrollable Content Area ---
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 16.dp, vertical = 20.dp)
                    .background(Color(0xFFE0F7FA), shape = RoundedCornerShape(20.dp)) // Very light cyan/blue inner background
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // --- Test Details and Student Responses ---
                item {
                    TestInfoAndResponsesCard(evaluationData)
                }

                // --- Tratamiento Section ---
                item {
                    TitledInputSection(
                        title = "Tratamiento",
                        textFieldValue = tratamientoText,
                        onValueChange = { tratamientoText = it },
                        placeholder = "Aquí se debe escribir el tratamiento que recomienda el especialista"
                    )
                }

                // --- Mi Criterio de Nivel de Ansiedad Section ---
                item {
                    TitledDropdownSection(
                        title = "Mi Criterio de Nivel de Ansiedad",
                        selectedOption = selectedNivelAnsiedad,
                        options = evaluationData.nivelesAnsiedadDisponibles,
                        onOptionSelected = {
                            selectedNivelAnsiedad = it
                            showNivelAnsiedadDropdown = false
                        },
                        expanded = showNivelAnsiedadDropdown,
                        onExpandedChange = { showNivelAnsiedadDropdown = it },
                        dropdownPlaceholder = "**Mostrar la lista de niveles de Ansiedad de este test** (que sea seleccionable)"
                    )
                }
            }

            // --- "Registrar Tratamiento" Button ---
            Button(
                onClick = {
                    // TODO: Implement registration logic
                    // Log details: evaluationData.testId, tratamientoText.text, selectedNivelAnsiedad
                    // navController.popBackStack() or navigate to a confirmation/next screen
                },
                modifier = Modifier
                    .fillMaxWidth(0.7f)
                    .padding(vertical = 24.dp)
                    .height(50.dp),
                shape = RoundedCornerShape(25.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFB3E5FC), // Light blue color, similar to MenuReviewTest "Volver"
                    contentColor = Color.DarkGray
                ),
                enabled = tratamientoText.text.isNotBlank() && selectedNivelAnsiedad != null // Enable only if fields are filled
            ) {
                Text(text = "Registrar Tratamiento", fontSize = 16.sp, fontWeight = FontWeight.Medium)
            }
        }
    }
}

@Composable
fun TestInfoAndResponsesCard(data: TestEvaluationDetails) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFFCE4EC) // Light pinkish background from image
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = "TEST DE ${data.testName}",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = Color.DarkGray
            )
            Text(
                text = "• PUNTAJE OBTENIDO: ${data.puntajeObtenido}",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.DarkGray,
                modifier = Modifier.padding(top = 4.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "• RESPUESTAS:",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.SemiBold,
                color = Color.DarkGray
            )
            // Displaying questions and answers
            Column(modifier = Modifier.padding(start = 8.dp, top = 4.dp)) {
                data.respuestas.take(3).forEach { qa -> // Show a few for brevity, or make scrollable if many
                    Text(
                        text = "${qa.pregunta}: \"${qa.respuestaEstudiante}\"",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.DarkGray.copy(alpha = 0.8f),
                        lineHeight = 16.sp
                    )
                }
                if (data.respuestas.size > 3) {
                    Text(
                        text = "...", // Indicate more answers
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.DarkGray.copy(alpha = 0.8f)
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TitledInputSection(
    title: String,
    textFieldValue: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    placeholder: String
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        // Overlapping Title Tag
        Text(
            text = title,
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.Bold,
            color = Color.DarkGray,
            modifier = Modifier
                .offset(y = 8.dp) // Pulls the title up a bit to overlap the card
                .zIndex(1f) // Ensures title is on top of the card
                .background(
                    Color(0xFFB2DFDB), // Light teal/cyan for title tag background
                    shape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp, bottomStart = 0.dp, bottomEnd = 0.dp) // Match image
                )
                .padding(horizontal = 20.dp, vertical = 8.dp)
        )

        OutlinedTextField(
            value = textFieldValue,
            onValueChange = onValueChange,
            modifier = Modifier
                .fillMaxWidth()
                .defaultMinSize(minHeight = 120.dp) // Make it a bit taller
                .border(1.dp, Color.Gray, RoundedCornerShape(12.dp)), // Border to match image
            placeholder = { Text(placeholder, style = MaterialTheme.typography.bodySmall) },
            shape = RoundedCornerShape(12.dp), // Rounded corners for the text field itself
            colors = TextFieldDefaults.outlinedTextFieldColors(
                containerColor = Color(0xFFE0F2F1), // Very light teal/cyan background for text field
                focusedBorderColor = Color.Transparent, // Remove default focus border
                unfocusedBorderColor = Color.Transparent // Remove default unfocused border
            )
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TitledDropdownSection(
    title: String,
    selectedOption: String?,
    options: List<String>,
    onOptionSelected: (String) -> Unit,
    expanded: Boolean,
    onExpandedChange: (Boolean) -> Unit,
    dropdownPlaceholder: String
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        // Overlapping Title Tag
        Text(
            text = title,
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.Bold,
            color = Color.DarkGray,
            modifier = Modifier
                .offset(y = 8.dp)
                .zIndex(1f)
                .background(
                    Color(0xFFB2DFDB), // Light teal/cyan for title tag background
                    shape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp, bottomStart = 0.dp, bottomEnd = 0.dp)
                )
                .padding(horizontal = 20.dp, vertical = 8.dp)
        )

        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = onExpandedChange,
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, Color.Gray, RoundedCornerShape(12.dp)) // Border to match image
                .background(Color(0xFFE0F2F1), RoundedCornerShape(12.dp)) // Background for the box
        ) {
            TextField(
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth(), // Important for the dropdown to take full width
                readOnly = true,
                value = selectedOption ?: dropdownPlaceholder,
                onValueChange = {}, // Not needed for readOnly
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                colors = ExposedDropdownMenuDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                ),
                textStyle = if (selectedOption == null) MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold) else MaterialTheme.typography.bodyLarge
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { onExpandedChange(false) },
                modifier = Modifier.background(Color.White) // Background for the dropdown menu items
            ) {
                options.forEach { selectionOption ->
                    DropdownMenuItem(
                        text = { Text(selectionOption) },
                        onClick = {
                            onOptionSelected(selectionOption)
                        },
                        contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true, device = "id:pixel_4")
@Composable
fun EvaluarTestScreenPreview() {
    MaterialTheme { // Wrap with MaterialTheme for previews to get default typography etc.
        EvaluarTestScreen(navController = rememberNavController(), testId = 1)
    }
}