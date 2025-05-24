package com.example.proyectosisvitag3.ui.view // Or your actual package

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed // Use itemsIndexed to get the index
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.proyectosisvitag3.ui.theme.SoftMint

// Define colors based on the image (consider moving to your Color.kt)
val LightBlueBackground = Color(0xFF27C8C8).copy(alpha = 0.3f)
val CreamBoxBackground = Color(0xFFFBF9F1)
val LightRedButton = Color(0xFFFFCDD2)
val DarkRedButtonText = Color(0xFFB71C1C)
val PillShapeBlue = Color(0xFFADD8E6) // A slightly darker blue for the "PREGUNTAS" pill


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CuestionarioScreen(
    navController: NavHostController,
    nombreTest: String, // Pass the name of the test
    cantPreguntas: Int // Number of questions
) {
    val preguntasText = remember {
        List(cantPreguntas) { index -> "PREGUNTA ${index + 1}:" }
    }
    val opcionesRespuesta = remember {
        listOf("No", "Leve", "Moderado", "Bastante")
    }
    val respuestasSeleccionadas = remember {
        mutableStateMapOf<Int, String?>().apply {
            preguntasText.forEachIndexed { index, _ -> put(index, null) }
        }
    }
    val allQuestionsAnswered = respuestasSeleccionadas.values.none { it == null }

    var showDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "TEST DE \"$nombreTest\"",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        color = Color.DarkGray
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Volver",
                            tint = Color.DarkGray
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = LightBlueBackground, // Match the background
                    titleContentColor = Color.DarkGray,
                ),
                modifier = Modifier.padding(bottom = 8.dp) // Add some space below TopAppBar
            )
        },
        containerColor = SoftMint // Overall screen background
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // "PREGUNTAS" Title Pill
            Box(
                modifier = Modifier
                    .padding(vertical = 12.dp)
                    .background(Color(0xFF92C7CF), RoundedCornerShape(80)) // Pill shape
                    .padding(horizontal = 32.dp, vertical = 10.dp)
            ) {
                Text(
                    text = "PREGUNTAS",
                    color = Color.DarkGray, // Or White if it looks better on the blue
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            }

            // Main content area with rounded corners and background
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(20.dp)) // Rounded corners for the blue area
                    .background(LightBlueBackground)
                    .padding(16.dp) // Inner padding for the blue area
            ) {
                LazyColumn(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(16.dp), // Space between question items
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    itemsIndexed(preguntasText) { index, pregunta ->
                        val selectedAnswer = respuestasSeleccionadas[index]
                        QuestionItem(
                            questionText = pregunta,
                            options = opcionesRespuesta,
                            selectedOption = selectedAnswer,
                            onOptionSelected = { option ->
                                respuestasSeleccionadas[index] = option
                            }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // "Enviar Test" Button
            Button(
                onClick = {
                    // TODO: Implement submit logic
                    // e.g., collect respuestasSeleccionadas and send them
                    showDialog = true
                },
                enabled = allQuestionsAnswered,
                modifier = Modifier
                    .fillMaxWidth(0.7f) // Button width
                    .height(50.dp)
                    .padding(bottom = 16.dp),
                shape = RoundedCornerShape(25.dp), // Pill shape
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (allQuestionsAnswered) LightRedButton else LightRedButton.copy(
                        alpha = 0.6f
                    ),
                    contentColor = DarkRedButtonText,
                    disabledContainerColor = LightRedButton.copy(alpha = 0.3f),
                    disabledContentColor = DarkRedButtonText.copy(alpha = 0.5f)
                )
            ) {
                Text(text = "Enviar Test", fontSize = 16.sp, fontWeight = FontWeight.Medium)
            }
        }

        if (showDialog) {
            AlertDialog(
                onDismissRequest = {
                    // Optional: Handle dialog dismissal by tapping outside or back button
                    showDialog = false
                    // You might want to navigate here as well if dismissing means "cancel"
                    // navController.navigate("studentMainScreen") { popUpTo("cuestionarioScreen") { inclusive = true } }
                },
                title = {
                    Text(
                        text = "Encuesta Registrada",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                },
                text = {
                    // You can add more descriptive text here if needed
                },
                confirmButton = {
                    Button(
                        onClick = {
                            showDialog = false
                            // Navigate to StudentMainScreen and clear CuestionarioScreen from back stack
                            navController.navigate("studentMainScreen") {
                                popUpTo(navController.graph.startDestinationId) // Clears back stack up to the start destination
                                launchSingleTop =
                                    true // Avoids multiple copies of studentMainScreen
                            }
                            // A more specific popUpTo if studentMainScreen isn't the start destination of the graph:
                            // navController.navigate("studentMainScreen") {
                            // popUpTo("cuestionarioScreen") { inclusive = true }
                            // launchSingleTop = true
                            // }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                horizontal = 30.dp,
                                vertical = 8.dp
                            ), // Adjust padding as needed
                        shape = RoundedCornerShape(25.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = LightRedButton, // Same as "Enviar Test"
                            contentColor = DarkRedButtonText   // Same as "Enviar Test"
                        )
                    ) {
                        Text("OK")
                    }
                },
                containerColor = CreamBoxBackground, // Optional: Set a background for the dialog
                shape = RoundedCornerShape(16.dp) // Optional: Rounded corners for the dialog
            )
        }
    }
}

@Composable
fun QuestionItem(
    questionText: String,
    options: List<String>,
    selectedOption: String?,
    onOptionSelected: (String) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth(0.95f) // Question box width relative to its parent
            .background(CreamBoxBackground, RoundedCornerShape(12.dp))
            .padding(16.dp)
    ) {
        Column {
            Text(
                text = questionText,
                style = MaterialTheme.typography.titleMedium, // Adjusted style
                fontWeight = FontWeight.Bold,
                color = Color.DarkGray,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            options.forEach { option ->
                AnswerOptionItem(
                    optionText = option,
                    isSelected = (option == selectedOption),
                    onSelected = { onOptionSelected(option) }
                )
            }
        }
    }
}

@Composable
fun AnswerOptionItem(
    optionText: String,
    isSelected: Boolean,
    onSelected: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp) // Reduced padding for options
            .clickable { onSelected() } // Make the whole row clickable
    ) {
        RadioButton(
            selected = isSelected,
            onClick = onSelected,
            colors = RadioButtonDefaults.colors(
                selectedColor = MaterialTheme.colorScheme.primary, // Or your preferred color
                unselectedColor = Color.Gray
            )
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = optionText,
            style = MaterialTheme.typography.bodyLarge, // Adjusted style
            color = Color.DarkGray
        )
    }
}

@Preview(showBackground = true, device = "id:pixel_4")
@Composable
fun PreviewCuestionarioScreen() {
    // Wrap with your theme if you have one for proper preview
    // ProyectoSisvitaG3Theme {
    CuestionarioScreen(
        navController = rememberNavController(),
        nombreTest = "Ejemplo de Ansiedad",
        cantPreguntas = 4
    )
    // }
}