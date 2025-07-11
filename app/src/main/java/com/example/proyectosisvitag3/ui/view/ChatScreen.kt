package com.example.proyectosisvitag3.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.proyectosisvitag3.R
import java.text.SimpleDateFormat
import java.util.*
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.proyectosisvitag3.ui.theme.ProyectoSisvitaG3Theme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen(navController: NavHostController) {
    val messages = remember { mutableStateListOf<Message>() }
    var inputText by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFE6E6)) // fondo rosa claro
            .padding(8.dp)
    ) {
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            reverseLayout = true // mensajes nuevos abajo
        ) {
            items(messages.reversed()) { message ->
                MessageBubble(message)
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextField(
                value = inputText,
                onValueChange = { inputText = it },
                modifier = Modifier.weight(1f),
                placeholder = { Text("Escribe un mensaje...") },
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.White
                )
            )
            Spacer(modifier = Modifier.width(4.dp))
            Button(
                onClick = {
                    if (inputText.isNotBlank()) {
                        messages.add(
                            Message(text = inputText, time = getCurrentTime(), isBot = false)
                        )
                        // Simular respuesta del bot (para el mock)
                        messages.add(
                            Message(text = "Respuesta del bot", time = getCurrentTime(), isBot = true)
                        )
                        inputText = ""
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00CFCF))
            ) {
                Text("Enviar", color = Color.White)
            }
        }
    }
}

@Composable
fun MessageBubble(message: Message) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = if (message.isBot) Arrangement.Start else Arrangement.End
    ) {
        if (message.isBot) {
            Image(
                painter = painterResource(id = R.drawable.logo_sisvita), // tu ícono
                contentDescription = null,
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.width(6.dp))
        }

        Column(
            horizontalAlignment = if (message.isBot) Alignment.Start else Alignment.End
        ) {
            Box(
                modifier = Modifier
                    .background(
                        if (message.isBot) Color.White else Color(0xFF00CFCF),
                        RoundedCornerShape(12.dp)
                    )
                    .padding(12.dp)
            ) {
                Text(message.text, color = if (message.isBot) Color.Black else Color.White)
            }
            Text(
                message.time,
                fontSize = 10.sp,
                color = Color(0xFF9B9BFF),
                modifier = Modifier.padding(top = 2.dp)
            )
        }
    }
}

data class Message(val text: String, val time: String, val isBot: Boolean)

fun getCurrentTime(): String {
    return SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date())
}

//Preview
@Preview(showBackground = true, name = "Chat Screen Preview")
@Composable
fun ChatScreenPreview() {
    // Es una buena práctica envolver tu preview con tu tema de la aplicación
    // si tienes uno definido, para que los estilos se apliquen correctamente.
    // Reemplaza ProyectoSisvitaG3Theme con el nombre real de tu tema si es diferente.
    ProyectoSisvitaG3Theme {
    }
}

@Preview(showBackground = true, name = "Message Bubble - User")
@Composable
fun MessageBubbleUserPreview() {
    ProyectoSisvitaG3Theme {
        MessageBubble(
            message = Message(
                text = "Hola, ¿cómo estás?",
                time = "10:00",
                isBot = false
            )
        )
    }
}

@Preview(showBackground = true, name = "Message Bubble - Bot")
@Composable
fun MessageBubbleBotPreview() {
    ProyectoSisvitaG3Theme {
        MessageBubble(
            message = Message(
                text = "Soy un bot respondiendo.",
                time = "10:01",
                isBot = true
            )
        )
    }
}