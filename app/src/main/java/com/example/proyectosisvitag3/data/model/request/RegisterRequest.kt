package com.example.proyectosisvitag3.data.model.request

data class RegisterRequest (
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String,
    val isSpecialist: Boolean = false, // Opcional, por defecto false
)