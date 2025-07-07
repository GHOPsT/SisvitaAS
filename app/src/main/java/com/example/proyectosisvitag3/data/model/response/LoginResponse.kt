package com.example.proyectosisvitag3.data.model.response
import com.example.proyectosisvitag3.data.model.response.RegisterResponse

data class LoginResponse(
    val message: String,
    val token: String,
    val user: UserResponse
)