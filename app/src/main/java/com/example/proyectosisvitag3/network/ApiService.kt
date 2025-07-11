package com.example.proyectosisvitag3.network

import com.example.proyectosisvitag3.data.model.request.*
import com.example.proyectosisvitag3.data.model.response.*
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiService {
    @POST("api/auth/register")
    fun register(
        @Body request: RegisterRequest
    ): Call<RegisterResponse>

    @POST("api/auth/login")
    fun login(
        @Body request: LoginRequest
    ): Response<LoginResponse>

    @POST("api/chatbot")
    fun chatbot(
        @Header("Authorization") token: String,
        @Body request: ChatRequest
    ): Response<ChatResponse>

    @POST("api/evaluarResults/actualizar")
    suspend fun update(
        @Body updateRequest: UpdateRequest
    ): Response<RegisterResponse>
}