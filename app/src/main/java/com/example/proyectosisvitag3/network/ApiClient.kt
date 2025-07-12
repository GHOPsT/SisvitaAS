// En: com/example/proyectosisvitag3/network/ApiClient.kt
package com.example.proyectosisvitag3.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private const val BASE_URL = "http://192.168.1.18:3000/" //Cambiar según la ruta del backend

    private val retrofit: Retrofit by lazy { // Hacemos retrofit privado si solo se usa para crear apiService
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    // Exponemos la instancia de ApiService
    val apiService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}
