package com.example.proyectosisvitag3.ui.theme.data.repository

import com.example.proyectosisvitag3.ui.theme.data.model.UpdateRequest
import com.example.proyectosisvitag3.ui.theme.data.model.RegisterResponse
import com.example.proyectosisvitag3.ui.theme.network.ApiInstance
import retrofit2.Response

class UpdateRepository {
    private val apiService = ApiInstance.apiInstance

    suspend fun update(updateRequest: UpdateRequest): Response<RegisterResponse> {
        return apiService.update(updateRequest)
    }
}