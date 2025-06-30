package com.example.proyectosisvitag3.ui.theme.network

import com.example.proyectosisvitag3.data.model.request.ChatRequest
import com.example.proyectosisvitag3.ui.theme.data.model.LoginRequest
import com.example.proyectosisvitag3.ui.theme.data.model.LoginResponse
import com.example.proyectosisvitag3.data.model.request.RegisterRequest
import com.example.proyectosisvitag3.data.model.response.RegisterResponse
import com.example.proyectosisvitag3.ui.theme.data.model.UpdateRequest
import com.example.proyectosisvitag3.ui.theme.data.model.UpdateResponse
import com.example.proyectosisvitag3.ui.theme.data.model.*
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.Response

interface ApiService {
    /*@POST("chatbot")
    suspend fun sendMessage(
        @Body request: ChatRequest
    ): Response<ChatResponse>

    @POST("Auth/v1/register")*/
    suspend fun register(
        @Body registerRequest: RegisterRequest
    ): Response<RegisterResponse>

    //@POST("Estudiantes/v1/login")
    suspend fun login(
        @Body loginRequest: LoginRequest
    ): Response<LoginResponse>

    //@POST("/EvaluarResults/v1/actualizar")
    suspend fun update(@Body updateRequest: UpdateRequest): Response<UpdateResponse>
}