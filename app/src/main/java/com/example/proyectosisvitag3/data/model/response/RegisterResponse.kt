package com.example.proyectosisvitag3.data.model.response
data class RegisterResponse(
    val success: Boolean,
    val message: String,
    val user: UserResponse
)

data class UserResponse(
    val id: Int,
    val email: String,
    val isSpecialist: Boolean
)