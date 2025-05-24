package com.example.proyectosisvitag3.ui.view // Or your correct package

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
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
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.proyectosisvitag3.R
import com.example.proyectosisvitag3.ui.components.ScreenHeader
import com.example.proyectosisvitag3.ui.theme.SoftMint

// --- ViewModel (Simplified for this example) ---
// In a real app, this would fetch user data
class StudentMainViewModel : ViewModel() {
    private val _userName = MutableLiveData("Nombre del Usuario")
    val userName: LiveData<String> = _userName

    private val _userLastName = MutableLiveData("Apellido del Usuario")
    val userLastName: LiveData<String> = _userLastName

    private val _userEmail = MutableLiveData("usuario@example.com")
    val userEmail: LiveData<String> = _userEmail

    // TODO: Add LiveData for user image URL/URI

    fun onNameChange(newName: String) {
        _userName.value = newName
    }
    fun onLastNameChange(newLastName: String) {
        _userLastName.value = newLastName
    }
    fun onEmailChange(newEmail: String) {
        _userEmail.value = newEmail
    }
}

// --- Composable Screen ---
@Composable
fun StudentMainScreen(
    navController: NavHostController,
    viewModel: StudentMainViewModel // Pass your ViewModel
) {
    val userName by viewModel.userName.observeAsState("")
    val userLastName by viewModel.userLastName.observeAsState("")
    val userEmail by viewModel.userEmail.observeAsState("")
    // val userImage = painterResource(id = R.drawable.ic_user_placeholder) // Replace with actual image loading

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = SoftMint // Or your desired background color
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 16.dp) // Padding at the bottom for the buttons
        ) {
            // --- Header: "MI PERFIL" ---
            ScreenHeader("MI PERFIL")

            Spacer(modifier = Modifier.height(24.dp))

            // --- User Image ---
            Image(
                painter = painterResource(id = R.drawable.logo_sisvita), // Placeholder, replace with actual user image
                contentDescription = "Imagen de Perfil",
                modifier = Modifier
                    .size(120.dp)
                    .align(Alignment.CenterHorizontally)
                    .padding(8.dp)
                    .background(Color.LightGray, shape = MaterialTheme.shapes.medium), // Placeholder background
                contentScale = ContentScale.Crop // Adjust as needed
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
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFFEFEFE))
                ) {
                    Column(
                        modifier = Modifier
                            .padding(start = 16.dp, end = 16.dp, bottom = 16.dp, top = 30.dp) // Top padding to make space for title
                    ) {
                        // Using Text for display. If these need to be editable, use CustomInputField
                        InfoRow(label = "Nombre:", value = userName)
                        Spacer(modifier = Modifier.height(12.dp))
                        InfoRow(label = "Apellido:", value = userLastName)
                        Spacer(modifier = Modifier.height(12.dp))
                        InfoRow(label = "Correo:", value = userEmail)

                        // If you need input fields (as suggested by "espacio para el nombre"):
                        /*
                        CustomInputField(
                            value = userName,
                            onValueChange = { viewModel.onNameChange(it) },
                            placeholder = "Nombre",
                            keyboardType = KeyboardType.Text,
                            label = "Nombre:" // Optional label for CustomInputField
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        CustomInputField(
                            value = userLastName,
                            onValueChange = { viewModel.onLastNameChange(it) },
                            placeholder = "Apellido",
                            keyboardType = KeyboardType.Text,
                            label = "Apellido:"
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        CustomInputField(
                            value = userEmail,
                            onValueChange = { viewModel.onEmailChange(it) },
                            placeholder = "Correo",
                            keyboardType = KeyboardType.Email,
                            label = "Correo:"
                        )
                        */
                    }
                }

                // Overlapping Title "Información Personal"
                Text(
                    text = "INFORMACION PERSONAL",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .offset(y = 8.dp) // Adjust offset to position it nicely over the card
                        .background(
                            Color(0xFF92C7CF), // Match card background for seamless look
                            shape = MaterialTheme.shapes.small
                        )
                        .padding(horizontal = 16.dp, vertical = 6.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp)) // Adds a 16dp space before the buttons

            // --- Action Buttons ---
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                ActionButton(
                    text = "REALIZAR TEST",
                    onClick = {
                        navController.navigate("menuTestScreen")
                    }
                )
                Spacer(modifier = Modifier.height(12.dp))
                ActionButton(
                    text = "VER MIS TEST",
                    onClick = {
                        // TODO: navController.navigate("testHistoryScreen_route")
                    }
                )
                Spacer(modifier = Modifier.weight(1f))
                ActionButton(
                    text = "CERRAR SESIÓN",
                    onClick = {
                        // TODO: Implement logout logic
                        // Example: navController.navigate("loginScreen_route") { popUpTo("studentMainScreen_route") { inclusive = true } }
                    },
                    containerColor = Color(0xFFF97A7A).copy(alpha = 0.5f), // Red color at 50% opacity
                    contentColor = MaterialTheme.colorScheme.onErrorContainer
                )
            }
        }
    }
}

@Composable
fun InfoRow(label: String, value: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.width(90.dp) // Adjust width as needed for alignment
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.8f)
        )
    }
}

@Composable
fun ActionButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    containerColor: Color = Color(0xFF7AF9CB),
    contentColor: Color = Color(0xFF000000)
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .height(48.dp),
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
fun StudentMainScreenPreview() {
    // ProyectoSisvitaG3Theme { // Apply your app's theme if you have one
    StudentMainScreen(
        navController = rememberNavController(),
        viewModel = StudentMainViewModel() // Use the example ViewModel for preview
    )
    // }
}