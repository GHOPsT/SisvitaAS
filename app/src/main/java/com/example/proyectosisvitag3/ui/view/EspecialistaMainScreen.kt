package com.example.proyectosisvitag3.ui.view // Or your correct package, e.g., com.example.proyectosisvitag3.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.proyectosisvitag3.R // Make sure this R is correct
import com.example.proyectosisvitag3.ui.components.ScreenHeader // Assuming ScreenHeader is in this package
import com.example.proyectosisvitag3.ui.theme.SoftMint // Or your defined background color

// --- ViewModel (Simplified for this example) ---
// In a real app, this would fetch specialist data
class EspecialistaMainViewModel : ViewModel() {
    private val _specialistName = MutableLiveData("NombreEspecialista")
    val specialistName: LiveData<String> = _specialistName

    private val _specialistLastName = MutableLiveData("ApellidoEspecialista")
    val specialistLastName: LiveData<String> = _specialistLastName

    private val _specialistSpecialties = MutableLiveData(listOf("Psicología Clínica", "Terapia Cognitivo-Conductual")) // Example list
    val specialistSpecialties: LiveData<List<String>> = _specialistSpecialties

    // TODO: Add LiveData for specialist image URL/URI

    fun onNameChange(newName: String) {
        _specialistName.value = newName
    }
    fun onLastNameChange(newLastName: String) {
        _specialistLastName.value = newLastName
    }
    fun onSpecialtiesChange(newSpecialties: List<String>) {
        _specialistSpecialties.value = newSpecialties
    }
}

// --- Composable Screen ---
@Composable
fun EspecialistaMainScreen(
    navController: NavHostController,
    viewModel: EspecialistaMainViewModel = viewModel() // Provide a default ViewModel instance
) {
    val specialistName by viewModel.specialistName.observeAsState("")
    val specialistLastName by viewModel.specialistLastName.observeAsState("")
    val specialistSpecialties by viewModel.specialistSpecialties.observeAsState(emptyList())

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = SoftMint // Background color from the image (light cyan/mint)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 16.dp) // Padding at the bottom for the buttons
        ) {
            // --- Header: "MI PERFIL" ---
            ScreenHeader(
                text = "MI PERFIL",
                // Based on the image, the header background seems a bit darker than SoftMint
                // You can adjust this color as needed.
                backgroundColor = Color(0xFFB2EBF2), // Light Blue-ish Gray, adjust as needed
                textColor = MaterialTheme.colorScheme.onSurfaceVariant // Or a specific dark color
            )

            Spacer(modifier = Modifier.height(24.dp))

            // --- Specialist Image ---
            Image(
                painter = painterResource(id = R.drawable.logo_sisvita), // REPLACE with your actual placeholder
                contentDescription = "Imagen de Perfil del Especialista",
                modifier = Modifier
                    .size(150.dp) // Slightly larger than student
                    .align(Alignment.CenterHorizontally)
                    .padding(8.dp)
                    // The image background in your screenshot is light blueish
                    .background(Color(0xFFD1EAF0), shape = MaterialTheme.shapes.extraLarge), // Circle shape
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(24.dp))

            // --- Personal Information Section ---
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                // Background Card for Personal Info
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp), // Space for the title to overlap
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White) // White card background
                ) {
                    Column(
                        modifier = Modifier
                            .padding(start = 16.dp, end = 16.dp, bottom = 16.dp, top = 30.dp) // Top padding for title
                    ) {
                        InfoRowEspecialista(label = "Nombre:", value = specialistName)
                        Spacer(modifier = Modifier.height(12.dp))
                        InfoRowEspecialista(label = "Apellido:", value = specialistLastName)
                        Spacer(modifier = Modifier.height(12.dp))
                        InfoRowEspecialista(label = "Especialidades:", value = specialistSpecialties.joinToString(", "))
                    }
                }

                // Overlapping Title "INFORMACION PERSONAL"
                Text(
                    text = "INFORMACION PERSONAL",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurfaceVariant, // Dark text
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .offset(y = 8.dp)
                        .background(
                            Color(0xFFBDBDBD), // Gray background for the title tag, adjust as needed from image
                            shape = MaterialTheme.shapes.small
                        )
                        .padding(horizontal = 16.dp, vertical = 6.dp)
                )
            }

            Spacer(modifier = Modifier.height(32.dp)) // More space before buttons

            // --- Action Buttons ---
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp), // More horizontal padding for buttons
                horizontalAlignment = Alignment.CenterHorizontally // Center buttons
            ) {
                ActionButtonEspecialista(
                    text = "VER TEST REALIZADOS",
                    onClick = {
                        // TODO: navController.navigate("completedTestsScreen_route")
                    },
                    // Teal button color from image
                    containerColor = Color(0xFF66D8C8)
                )
                Spacer(modifier = Modifier.height(16.dp))
                ActionButtonEspecialista(
                    text = "AÑADIR ESPECIALIDAD",
                    onClick = {
                        // TODO: navController.navigate("addSpecialtyScreen_route")
                    },
                    // Teal button color from image
                    containerColor = Color(0xFF66D8C8)
                )
                Spacer(modifier = Modifier.height(16.dp))
                ActionButtonEspecialista(
                    text = "AÑADIR TEST",
                    onClick = {
                        navController.navigate("agregaTestScreen")
                    },
                    // Teal button color from image
                    containerColor = Color(0xFF66D8C8)
                )

                Spacer(modifier = Modifier.weight(1f)) // Pushes logout button to the bottom

                ActionButtonEspecialista(
                    text = "CERRAR SESIÓN",
                    onClick = {
                        // TODO: Implement logout logic
                        // Example: navController.navigate("loginScreen") { popUpTo("especialistaMainScreen") { inclusive = true } }
                    },
                    // Pinkish button color from image
                    containerColor = Color(0xFFF4A7A7),
                    contentColor = MaterialTheme.colorScheme.onErrorContainer // Or a specific dark red
                )
            }
        }
    }
}

@Composable
fun InfoRowEspecialista(label: String, value: String) {
    Row(verticalAlignment = Alignment.Top) { // Align to top for potentially multi-line specialties
        Text(
            text = label,
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.width(120.dp) // Adjust width as needed for alignment
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyLarge,
            color = Color(0xFF00796B) // Blueish text color from image
        )
    }
}

@Composable
fun ActionButtonEspecialista(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    containerColor: Color, // Made container color mandatory to match image style
    contentColor: Color = Color.DarkGray // Default dark text for buttons
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth(0.85f) // Buttons don't span full width
            .height(52.dp),
        shape = RoundedCornerShape(26.dp), // More rounded corners
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = contentColor
        )
    ) {
        Text(text = text, fontWeight = FontWeight.Medium)
    }
}

@Preview(showBackground = true, device = "id:pixel_4")
@Composable
fun EspecialistaMainScreenPreview() {
    // ProyectoSisvitaG3Theme { // Apply your app's theme if you have one
    EspecialistaMainScreen(
        navController = rememberNavController(),
        viewModel = EspecialistaMainViewModel() // Use the example ViewModel for preview
    )
    // }
}