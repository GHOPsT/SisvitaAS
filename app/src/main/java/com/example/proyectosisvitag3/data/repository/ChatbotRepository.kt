package com.example.proyectosisvitag3.data.repository

import  com.example.proyectosisvitag3.data.model.response.ChatRequest
import com.example.proyectosisvitag3.data.model.response.ChatResponse
import com.example.proyectosisvitag3.network.ApiInstance
import retrofit2.Response

class ChatbotRepository {
    private val apiService = ApiInstance.apiService

    suspend fun sendMessage(token: String, chatRequest: ChatRequest): Response<ChatResponse> {
        return apiService.chatbot("Bearer $token", chatRequest)
    }
}