package com.example.proyectosisvitag3.data.repository

import com.example.proyectosisvitag3.data.model.request.LoginRequest
import com.example.proyectosisvitag3.data.model.response.LoginResponse
import com.example.proyectosisvitag3.network.ApiInstance
import retrofit2.Response

class LoginRepository {
    private val apiService = ApiInstance.apiService

    suspend fun login(loginRequest: LoginRequest): Response<LoginResponse> {
        return apiService.login(loginRequest)
    }
}