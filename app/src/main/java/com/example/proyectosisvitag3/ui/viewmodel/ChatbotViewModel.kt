package com.example.proyectosisvitag3.ui.viewmodel

import androidx.content.Context
import androidx.lifecycle.*
import com.example.proyectosisvitag3.data.model.request.ChatRequest
import com.example.proyectosisvitag3.data.model.response.ChatResponse
import com.example.proyectosisvitag3.data.repository.ChatbotRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class ChatbotViewModel : ViewModel()  {
    private val repository = ChatbotRepository()
    val chatReply = MutableLiveData<String?>()
    val error = MutableLiveData<String?>()

    fun sendMessage(context: Context, message: String) {
        viewModelScope.launch {
            try {
                val token = getToken(context)
                if (token = null) {
                    error.value = "No se encontró el token de autenticación"
                    return@launch
                }
                val response: Response<ChatResponse> = repository.sendMessage(token, ChatRequest(message))
                if (response.isSuccessful) {
                    chatReply.value = response.body()?.reply
                } else {
                    error.value = response.message()
                }
            } catch (e: Exception) {
                error.value = e.message
            }
        }
    }
    private fun getToken(context: Context) {
        val prefs = context.getSharedPreferences("auth_prefs", Context.MODE_PRIVATE)
        return prefs.getString("jwt_token", null)
    }
}