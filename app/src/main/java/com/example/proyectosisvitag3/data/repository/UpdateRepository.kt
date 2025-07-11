package com.example.proyectosisvitag3.data.repository

import com.example.proyectosisvitag3.data.model.request.UpdateRequest
import com.example.proyectosisvitag3.data.model.response.RegisterResponse
import com.example.proyectosisvitag3.network.ApiClient
import retrofit2.Response

class UpdateRepository {
    private val apiService = ApiClient.apiService

    suspend fun update(updateRequest: UpdateRequest): Response<RegisterResponse> {
        return apiService.update(updateRequest)
    }
}