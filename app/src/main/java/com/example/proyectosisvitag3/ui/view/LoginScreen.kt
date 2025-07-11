package com.example.proyectosisvitag3.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import kotlinx.coroutines.launch
import com.example.proyectosisvitag3.R
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.proyectosisvitag3.ui.components.CustomInputField
import com.example.proyectosisvitag3.ui.viewmodel.LoginViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavHostController, viewModel: LoginViewModel) {
    val userType = navController.currentBackStackEntry
        ?.arguments?.getString("userType") ?: "estudiante" // default
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFFEFFFFF)
    ) {
        Box(
            Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            // El composable Login maneja la lógica y el estado de los campos y botones.
            Login(
                modifier = Modifier.align(Alignment.Center),
                viewModel = viewModel,
                userType = userType,
                onLoginSuccess = {
                    if (userType == "estudiante") {
                        navController.navigate("studentMainScreen")
                    } else {
                        navController.navigate("EspecialistaMainScreen") }
                                 }, // Pasa la acción de navegación
                onRegisterClick = { navController.navigate("registerScreen/$userType") }     // Pasa la acción de navegación
            )
        }
    }
}

@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreenPreview() {
    val navController = rememberNavController()
    val viewModel = LoginViewModel()
    LoginScreen(navController, viewModel)
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Login(
    modifier: Modifier,
    viewModel: LoginViewModel,
    onLoginSuccess: () -> Unit, // Lambda para manejar la navegación al éxito
    onRegisterClick: () -> Unit, // Lambda para manejar el clic en registrarse
    userType: String,
) {
    val email: String by viewModel.email.observeAsState(initial = "")
    val password: String by viewModel.password.observeAsState(initial = "")
    val loginEnable: Boolean by viewModel.loginEnable.observeAsState(initial = false)
    val isLoading: Boolean by viewModel.isLoading.observeAsState(initial = false)
    val coroutineScope = rememberCoroutineScope()
    val loginSuccess: Boolean by viewModel.loginSuccess.observeAsState(initial = false) // Observe loginSuccess

    // Observa el estado de carga y navega si es exitoso
    LaunchedEffect(loginSuccess) { // Observe loginSuccess instead of isLoading
        if (loginSuccess) {
            onLoginSuccess() // Navigate when loginSuccess is true
            viewModel.resetLoginSuccessState() // Reset the state after navigation
        }
    }


    Column(modifier = modifier) {
        LoginHeaderImage( // Renombrado para ser más específico del login
            Modifier
                .align(Alignment.CenterHorizontally)
                .size(200.dp)
        )
        Spacer(modifier = Modifier.padding(16.dp))
        EmailInputField( // Renombrado y simplificado
            email = email,
            onEmailChange = { viewModel.onLoginChanged(it, password) } // Pasa el evento al ViewModel
        )
        Spacer(modifier = Modifier.padding(4.dp))
        PasswordInputField( // Renombrado y simplificado
            password = password,
            onPasswordChange = { viewModel.onLoginChanged(email, it) } // Pasa el evento al ViewModel
        )
        Spacer(modifier = Modifier.padding(8.dp))
        ForgotPasswordText(Modifier.align(Alignment.End)) // Renombrado
        Spacer(modifier = Modifier.padding(16.dp))
        LoginButton(
            loginEnable = loginEnable,
            onLoginClick = { // Pasa el evento al ViewModel
                coroutineScope.launch {
                    viewModel.onLoginSelected()
                }
            }
        )
        Spacer(modifier = Modifier.height(12.dp))
        RegisterButton(onRegisterClick = onRegisterClick) // Pasa la lambda de navegación
    }
}

// Componentes más pequeños y reutilizables

@Composable
fun LoginHeaderImage(modifier: Modifier) {
    Image(
        painter = painterResource(id = R.drawable.logo_sisvita),
        contentDescription = "Header",
        modifier = modifier
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmailInputField(email: String, onEmailChange: (String) -> Unit) {
    CustomInputField(
        value = email,
        onValueChange = onEmailChange,
        placeholder = "Usuario o Correo electrónico",
        keyboardType = KeyboardType.Email
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordInputField(password: String, onPasswordChange: (String) -> Unit) {
    CustomInputField(
        value = password,
        onValueChange = onPasswordChange,
        placeholder = "Contraseña",
        keyboardType = KeyboardType.Password,
        visualTransformation = PasswordVisualTransformation()
    )
}

@Composable
fun ForgotPasswordText(modifier: Modifier) {
    Text(
        text = "¿Olvidaste la contraseña?",
        modifier = modifier.clickable {
            // TODO: Implementar acción de olvidar contraseña
        },
        fontSize = 12.sp,
        fontWeight = FontWeight.Bold,
        color = Color(0xFF27C8C8)
    )
}

@Composable
fun LoginButton(loginEnable: Boolean, onLoginClick: () -> Unit) {
    Button(
        onClick = onLoginClick, // Usa la lambda para el clic
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF27C8C8),
            disabledContainerColor = Color(0xFF27C8C8),
            contentColor = Color.White,
            disabledContentColor = Color.White
        ), enabled = loginEnable
    ) {
        Text(text = "Iniciar sesión")
    }
}

@Composable
fun RegisterButton(onRegisterClick: () -> Unit) {
    Button(
        onClick = onRegisterClick, // Usa la lambda para el clic
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF27C8C8),
            disabledContainerColor = Color(0xFF27C8C8),
            contentColor = Color.White,
            disabledContentColor = Color.White
        )
    ) {
        Text(text = "Registrarse")
    }
}