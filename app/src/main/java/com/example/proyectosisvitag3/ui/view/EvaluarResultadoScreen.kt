package com.example.proyectosisvitag3.ui.theme.iu

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import kotlinx.coroutines.launch
import com.example.proyectosisvitag3.R
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import androidx.compose.runtime.Composable

@Composable
fun EvaluarResultadoScreen(navController: NavHostController, viewModel: EvaluarResultadoViewModel){
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFFEFFFFF)
    ) {
        Box(
            Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            EvaluarResultado(Modifier.align(Alignment.Center), viewModel, navController)
        }
    }
}

@Composable
fun EvaluarResultado(modifier: Modifier, viewModel: EvaluarResultadoViewModel, navController: NavHostController) {
    val nivelAnsiedad: String by viewModel.nivelAnsiedad.observeAsState(initial = "")
    val observaciones: String by viewModel.observaciones.observeAsState(initial = "")
    val updateEnable: Boolean by viewModel.updateEnable.observeAsState(initial = false)
    val coroutineScope = rememberCoroutineScope()

    val isLoading: Boolean by viewModel.isLoading.observeAsState(initial = false)

    if (isLoading) {
        navController.navigate("ListarScreen")
    } else {
        Column(modifier = modifier) {
            Text(
                text = "Evaluar Resultado del Test",
                style = TextStyle(fontSize = 45.sp, color = Color.Black, fontFamily = FontFamily.Cursive)
            )
            Spacer(modifier = Modifier.padding(16.dp))
            NivelAnsiedadField(nivelAnsiedad) { viewModel.onUpdateChanged(it, observaciones) }
            Spacer(modifier = Modifier.padding(4.dp))
            ObservacionesField(observaciones) { viewModel.onUpdateChanged(nivelAnsiedad, it) }
            Spacer(modifier = Modifier.padding(8.dp))
            UpdateButton(updateEnable) {
                coroutineScope.launch {
                    viewModel.onUpdateSelected()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NivelAnsiedadField(nivelAnsiedad: String, onTextFieldChanged: (String) -> Unit) {
    TextField(
        value = nivelAnsiedad, onValueChange = { onTextFieldChanged(it) },
        placeholder = { Text(text = "Nivel de Ansiedad") },
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        singleLine = true,
        maxLines = 1,
        colors = TextFieldDefaults.textFieldColors(
            containerColor = Color(0xFFDEDDDD),
            cursorColor = Color(0xFF636262),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            focusedTextColor = Color(0xFF000000),
            unfocusedTextColor = Color(0xFF000000)
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ObservacionesField(observaciones: String, onTextFieldChanged: (String) -> Unit) {
    TextField(
        value = observaciones, onValueChange = { onTextFieldChanged(it) },
        placeholder = { Text(text = "Observaciones") },
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        colors = TextFieldDefaults.textFieldColors(
            containerColor = Color(0xFFDEDDDD),
            cursorColor = Color(0xFF636262),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            focusedTextColor = Color(0xFF000000),
            unfocusedTextColor = Color(0xFF000000)
        )
    )
}

@Composable
fun UpdateButton(updateEnable: Boolean, onUpdateSelected: () -> Unit) {
    Button(
        onClick = { onUpdateSelected() },
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFFFF4303),
            disabledContainerColor = Color(0xFFF78058),
            contentColor = Color.White,
            disabledContentColor = Color.White
        ), enabled = updateEnable
    ) {
        Text(text = "Actualizar")
    }
}

/* @Preview(showBackground = true)
@Composable
fun EvaluarResultadoScreenPreview() {
    EvaluarResultadoScreen(rememberNavController(), viewModel: EvaluarResultadoViewModel)
}*/