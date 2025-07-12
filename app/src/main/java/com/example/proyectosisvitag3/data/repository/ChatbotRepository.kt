package com.example.proyectosisvitag3.data.repository

import com.example.proyectosisvitag3.data.model.request.ChatRequest
import com.example.proyectosisvitag3.data.model.response.ChatResponse
import com.example.proyectosisvitag3.network.ApiClient
import retrofit2.Response

class ChatbotRepository {
    private val apiService = ApiClient.apiService

    suspend fun sendMessage(token: String?, chatRequest: ChatRequest): Response<ChatResponse> {
        return apiService.chatbot(token ?: "", chatRequest)
    }
}