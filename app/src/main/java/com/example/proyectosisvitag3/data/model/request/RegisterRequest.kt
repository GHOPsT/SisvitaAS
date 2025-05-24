package com.example.proyectosisvitag3.data.model.request

data class RegisterRequest (
    val nombres: String,
    val apellidos: String,
    val email: String,
    val password: String,
    val tipoUsuario: String,  // "estudiante" o "especialista"
)