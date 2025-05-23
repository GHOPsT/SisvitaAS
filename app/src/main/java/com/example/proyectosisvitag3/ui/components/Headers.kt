package com.example.proyectosisvitag3.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun ScreenHeader(
    text: String,
    modifier: Modifier = Modifier, // Allow passing custom modifiers
    backgroundColor: Color = Color(0xFFAAD7D9), // Default background
    textColor: Color = MaterialTheme.colorScheme.onPrimaryContainer, // Default text color
    textStyle: androidx.compose.ui.text.TextStyle = MaterialTheme.typography.headlineSmall,
    fontWeight: FontWeight = FontWeight.Bold
) {
    Box(
        modifier = modifier // Apply the passed modifier first
            .fillMaxWidth()
            .height(70.dp) // You might want to make height configurable too
            .background(backgroundColor),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            style = textStyle,
            fontWeight = fontWeight,
            color = textColor
        )
    }
}