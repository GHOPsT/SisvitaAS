package com.example.proyectosisvitag3.ui.theme.iu

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun EspecialistaMainScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFEFFFFF))
            .padding(16.dp),
        contentAlignment = androidx.compose.ui.Alignment.Center
    ) {
        Text(text = "Pantalla principal del Especialista")
    }
}

@Preview
@Composable
fun EspecialistaMainScreenPreview() {
    EspecialistaMainScreen()
}
