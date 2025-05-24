package com.example.proyectosisvitag3.data.model.response

data class RegisterResponse (
    val success: Boolean,
    val message: String,
    val userId: Int? = null,  // ID común
    val tipoUsuario: String? = null  // "estudiante" o "especialista"
)