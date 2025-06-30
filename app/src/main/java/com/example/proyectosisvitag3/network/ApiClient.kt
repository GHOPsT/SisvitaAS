package com.example.proyectosisvitag3.network
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import com.example.proyectosisvitag3.ui.theme.network.ApiService
object ApiClient {
    /*private const val BASE_URL = "http://localhost:3000/api/" // Cambiar por tu URL
    //Configura el interceptor de logs
    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
    //Crea el cliente OkHttpClient con el interceptor
    private val httpClient = OkHttpClient.Builder()
        .addInterceptor { loggingInterceptor }
        .build()
    //Crea el retrofit
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient)
            .build()
    }
    //Crea la instancia dle servicio
    val apiService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }*/

    //Mock para pruebas (eliminar cuando se implemente la funcionalidad)
    val apiService: ApiService by lazy {
        MockApiService()
    }
}
