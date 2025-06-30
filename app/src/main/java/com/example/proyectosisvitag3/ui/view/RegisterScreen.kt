package com.example.proyectosisvitag3.ui.view

//import androidx.activity.result.launch
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.proyectosisvitag3.R
import com.example.proyectosisvitag3.ui.components.CustomInputField
import com.example.proyectosisvitag3.ui.theme.SoftMint
import com.example.proyectosisvitag3.ui.viewmodel.RegisterViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(
    navController: NavHostController,
    viewModel: RegisterViewModel
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = SoftMint
    ) {
        Box(
            Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            // Scrollable content for longer forms
            Column(
                modifier = Modifier
                    .align(Alignment.Center)
                    .verticalScroll(rememberScrollState()), // Make content scrollable
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                RegisterForm(
                    viewModel = viewModel,
                    onRegisterSuccess = { navController.navigate("loginScreen") }, // Navigate to login on success
                    onLoginClick = { navController.navigate("loginScreen") } // Navigate to login if user wants to go back
                )
            }
        }
    }
}

@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreenPreview() {
    val navController = rememberNavController()
    val viewModel = RegisterViewModel()
    RegisterScreen(navController = navController, viewModel = viewModel)
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterForm(
    viewModel: RegisterViewModel,
    onRegisterSuccess: () -> Unit,
    onLoginClick: () -> Unit
) {
    // Observe states from your RegisterViewModel (replace with actual ViewModel states)
    val firstName: String by viewModel.firstName.observeAsState(initial = "") // New state for Nombres
    val lastName: String by viewModel.lastName.observeAsState(initial = "")   // New state for Apellidos
    val email: String by viewModel.email.observeAsState(initial = "")
    val password: String by viewModel.password.observeAsState(initial = "")
    val confirmPassword: String by viewModel.confirmPassword.observeAsState(initial = "")
    val registerEnable: Boolean by viewModel.registerEnable.observeAsState(initial = false)
    val isLoading: Boolean by viewModel.isLoading.observeAsState(initial = false)
    val registerSuccess: Boolean by viewModel.registerSuccess.observeAsState(initial = false)
    val userType: String by viewModel.userType.observeAsState(initial = "estudiante")

    val coroutineScope = rememberCoroutineScope()

    // LaunchedEffect to handle navigation on successful registration
    LaunchedEffect(registerSuccess) {
        if (registerSuccess) {
            onRegisterSuccess()
            viewModel.resetRegisterSuccessState() // Reset state after navigation
        }
    }

    // Title or logo for the registration screen
    // Replace with your actual logo or title composable
    Image(
        painter = painterResource(id = R.drawable.logo_sisvita),
        contentDescription = "Header",
        modifier = Modifier.height(100.dp)
    )
    Spacer(modifier = Modifier.height(24.dp))

    // Input fields using the CustomInputField
    CustomInputField(
        value = firstName,
        onValueChange = { viewModel.onFirstNameChange(it) }, // Implement this in ViewModel
        placeholder = "Nombres",
        keyboardType = KeyboardType.Text
    )
    Spacer(modifier = Modifier.height(16.dp))

    // Input field for "Apellidos"
    CustomInputField(
        value = lastName,
        onValueChange = { viewModel.onLastNameChange(it) }, // Implement this in ViewModel
        placeholder = "Apellidos",
        keyboardType = KeyboardType.Text
    )
    Spacer(modifier = Modifier.height(16.dp))

    CustomInputField(
        value = email,
        onValueChange = { viewModel.onEmailChange(it) }, // Implement this in ViewModel
        placeholder = "Correo electrónico",
        keyboardType = KeyboardType.Email
    )
    Spacer(modifier = Modifier.height(16.dp))

    CustomInputField(
        value = password,
        onValueChange = { viewModel.onPasswordChange(it) }, // Implement this in ViewModel
        placeholder = "Contraseña",
        keyboardType = KeyboardType.Password,
        visualTransformation = PasswordVisualTransformation()
    )
    Spacer(modifier = Modifier.height(16.dp))

    CustomInputField(
        value = confirmPassword,
        onValueChange = { viewModel.onConfirmPasswordChange(it) }, // Implement this in ViewModel
        placeholder = "Confirmar Contraseña",
        keyboardType = KeyboardType.Password,
        visualTransformation = PasswordVisualTransformation()
    )
    Spacer(modifier = Modifier.height(24.dp))

    Spacer(modifier = Modifier.height(16.dp))

    // Selector de tipo de usuario
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text("Tipo de usuario:", modifier = Modifier.padding(end = 8.dp))
        RadioButton(
            selected = userType == "estudiante",
            onClick = { viewModel.onUserTypeChange("estudiante") }
        )
        Text("Estudiante", modifier = Modifier.padding(end = 16.dp))
        RadioButton(
            selected = userType == "especialista",
            onClick = { viewModel.onUserTypeChange("especialista") }
        )
        Text("Especialista")
    }
    Spacer(modifier = Modifier.height(16.dp))

    // Register Button
    Button(
        onClick = {
            coroutineScope.launch {
                viewModel.onRegisterSelected() // Implement this in ViewModel
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF27C8C8), // Example button color
            disabledContainerColor = Color(0xFF27C8C8).copy(alpha = 0.5f),
            contentColor = Color.White,
            disabledContentColor = Color.White
        ),
        enabled = registerEnable && !isLoading // Enable based on validation and loading state
    ) {
        Text(text = if (isLoading) "Registrando..." else "Registrarse")
    }
    Spacer(modifier = Modifier.height(16.dp))

    // Button to go back to Login
    TextButton(onClick = onLoginClick) {
        Text("¿Ya tienes una cuenta? Inicia sesión")
    }
}

private fun RegisterViewModel.resetRegisterSuccessState() {
    TODO("Not yet implemented")
}
