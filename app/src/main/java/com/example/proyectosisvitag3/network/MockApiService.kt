package com.example.proyectosisvitag3.network // O el paquete donde quieras ubicarlo, ej: com.example.proyectosisvitag3.network.mocks

import com.example.proyectosisvitag3.data.model.request.RegisterRequest
import com.example.proyectosisvitag3.data.model.request.LoginRequest
import com.example.proyectosisvitag3.data.model.response.LoginResponse
import com.example.proyectosisvitag3.data.model.request.UpdateRequest
import com.example.proyectosisvitag3.data.model.response.RegisterResponse
//import com.google.firebase.appdistribution.gradle.ApiService
// Importa otros modelos de request/response si los usas para otros métodos mockeados
// import com.example.proyectosisvitag3.data.model.request.LoginRequest
// import com.example.proyectosisvitag3.data.model.response.LoginResponse
// import com.example.proyectosisvitag3.ui.theme.data.model.UpdateRequest
// import com.example.proyectosisvitag3.ui.theme.data.model.UpdateResponse
import kotlinx.coroutines.delay
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.Response

class MockApiService : ApiService { // Implementa la interfaz

    // Variable para simular diferentes escenarios de registro
    var simulateRegistrationSuccess: Boolean = true
    var simulateNetworkError: Boolean = false
    var simulateSpecificErrorCode: Int? = null // Ej: 409 para conflicto (usuario ya existe)

    override suspend fun register(registerRequest: RegisterRequest): Response<RegisterResponse> {
        // Simula un retraso de red para que parezca más real
        delay(1000) // Espera 1 segundo

        if (simulateNetworkError) {
            // Esto no es exactamente un error de red que Retrofit lanzaría como IOException,
            // pero podemos simular un cuerpo de error genérico.
            // Una forma más realista sería lanzar una IOException directamente,
            // pero eso requeriría manejo de excepciones en el ViewModel diferente al de Response.
            val errorBody = """{"success":false, "message":"Error de red simulado"}""".toResponseBody("application/json".toMediaTypeOrNull())
            return Response.error(503, errorBody) // Service Unavailable
        }

        if (simulateSpecificErrorCode != null) {
            val errorCode = simulateSpecificErrorCode!!
            val errorMessage = when (errorCode) {
                400 -> "Datos de registro inválidos (simulado)."
                409 -> "El usuario con email ${registerRequest.email} ya existe (simulado)."
                500 -> "Error interno del servidor simulado."
                else -> "Error desconocido simulado."
            }
            val errorBody = """{"success":false, "message":"$errorMessage"}""".toResponseBody("application/json".toMediaTypeOrNull())
            return Response.error(errorCode, errorBody)
        }

        return if (simulateRegistrationSuccess) {
            // Simula un registro exitoso
            val mockSuccessResponse = RegisterResponse(
                success = true,
                message = "¡Registro simulado exitoso para ${registerRequest.nombres}!",
                // data = null // O algún RegisterResponse.Data si tu modelo lo tiene y es necesario
            )
            Response.success(mockSuccessResponse)
        } else {
            // Simula un fallo lógico en el registro (ej. email ya existe, contraseña débil, etc.)
            // pero la llamada HTTP en sí fue "exitosa" (código 200)
            val mockFailureResponse = RegisterResponse(
                success = false,
                message = "El registro falló: el email ${registerRequest.email} ya está en uso (simulado).",
                // data = null
            )
            // Para que el ViewModel lo interprete como error lógico,
            // aún podemos devolver Response.success pero con `success = false` en el cuerpo.
            // O, si prefieres un código HTTP de error como un 400 (Bad Request) o 409 (Conflict):
            // Response.error(409, gson.toJson(mockFailureResponse).toResponseBody("application/json".toMediaTypeOrNull()))
            // Por simplicidad, mantendremos Response.success con success=false en el body
            Response.success(mockFailureResponse)
        }
    }

    override suspend fun login(loginRequest: LoginRequest): Response<LoginResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun update(updateRequest: UpdateRequest): Response<RegisterResponse> {
        TODO("Not yet implemented")
    }

    // --- Implementa aquí otros métodos de ApiService ---

    /*
    override suspend fun login(loginRequest: LoginRequest): Response<LoginResponse> {
        delay(1000)
        // Lógica de simulación para login
        if (loginRequest.email == "test@example.com" && loginRequest.password == "password") {
            return Response.success(LoginResponse(success = true, message = "Login simulado exitoso", token = "fake-jwt-token", data = null))
        } else {
            val errorBody = """{"success":false, "message":"Credenciales inválidas (simulado)"}""".toResponseBody("application/json".toMediaTypeOrNull())
            return Response.error(401, errorBody) // Unauthorized
        }
    }

    override suspend fun update(updateRequest: UpdateRequest): Response<UpdateResponse> {
        delay(500)
        // Lógica de simulación para update
        return Response.success(UpdateResponse(success = true, message = "Actualización simulada exitosa", data = null))
    }
    */
}