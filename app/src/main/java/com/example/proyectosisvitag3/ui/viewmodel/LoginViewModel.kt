package com.example.proyectosisvitag3.ui.viewmodel

import android.util.Patterns // Para validación de email
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyectosisvitag3.data.model.request.LoginRequest
import com.example.proyectosisvitag3.data.model.response.LoginResponse // Asegúrate de tener esta clase
import com.example.proyectosisvitag3.data.repository.LoginRepository
import kotlinx.coroutines.launch
// Quita la importación de Context si no la usas más directamente en la función login
// import android.content.Context

class LoginViewModel : ViewModel() {
    private val repository = LoginRepository()

    // 1. LiveData para los campos de entrada
    private val _email = MutableLiveData<String>("")
    val email: LiveData<String> = _email

    private val _password = MutableLiveData<String>("")
    val password: LiveData<String> = _password

    // LiveData para el resultado del login
    private val _loginResult = MutableLiveData<LoginResponse?>() // Cambiado para que coincida con el uso
    val loginResult: LiveData<LoginResponse?> = _loginResult

    // LiveData para errores
    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    // LiveData para el estado de carga
    private val _isLoading = MutableLiveData<Boolean>(false)
    val isLoading: LiveData<Boolean> = _isLoading

    // LiveData para habilitar el botón de login
    private val _loginEnable = MutableLiveData<Boolean>(false)
    val loginEnable: LiveData<Boolean> = _loginEnable

    // LiveData para indicar el éxito del login y disparar la navegación
    private val _loginSuccess = MutableLiveData<Boolean>(false)
    val loginSuccess: LiveData<Boolean> = _loginSuccess


    // 2. Función para manejar los cambios en los campos de entrada
    fun onLoginChanged(emailInput: String, passwordInput: String) {
        _email.value = emailInput
        _password.value = passwordInput
        _loginEnable.value = isValidEmail(emailInput) && isValidPassword(passwordInput)
    }

    private fun isValidEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches() && email.isNotEmpty()
    }

    private fun isValidPassword(password: String): Boolean {
        // Añade aquí tu lógica de validación de contraseña si es necesaria (ej. longitud mínima)
        return password.isNotEmpty() && password.length >= 6 // Ejemplo: mínimo 6 caracteres
    }

    // 3. Función que se llama cuando se presiona el botón de login
    // Ya no necesitas pasar email y password como parámetros aquí, ya que los tomará de _email y _password
    fun onLoginSelected() { // Removido context, email, password de los parámetros
        if (!_loginEnable.value!!) { // Si no está habilitado, no hacer nada
            return
        }
        viewModelScope.launch {
            _isLoading.value = true
            try {
                // Usa los valores de los LiveData internos
                val currentEmail = _email.value ?: ""
                val currentPassword = _password.value ?: ""
                val response = repository.login(LoginRequest(currentEmail, currentPassword))

                if (response.isSuccessful) {
                    val loginResponse = response.body()
                    _loginResult.value = loginResponse
                    _loginSuccess.value = true // Indica que el login fue exitoso
                    // El guardado del token se podría mover aquí si no depende del contexto directamente
                    // o manejarlo en la Activity/Fragment después de observar loginSuccess
                    // Ejemplo: loginResponse?.token?.let { saveToken(it) } // Necesitarías una forma de pasar el token
                } else {
                    _error.value = "Error ${response.code()}: ${response.message()}"
                    _loginSuccess.value = false
                }
            } catch (e: Exception) {
                _error.value = e.message ?: "Ocurrió un error desconocido"
                _loginSuccess.value = false
            } finally {
                _isLoading.value = false
            }
        }
    }

    // Función para resetear el estado de loginSuccess después de la navegación
    fun resetLoginSuccessState() {
        _loginSuccess.value = false
    }


    // El guardado del token podría requerir el contexto.
    // Una opción es pasar el token a la UI y que la Activity/Fragment lo guarde,
    // o inyectar ApplicationContext en el ViewModel (con Hilt es más fácil).
    // Por ahora, lo comentaré para simplificar, pero necesitarás manejar esto.
    /*
    private fun saveToken(context: Context, token: String) {
        val prefs = context.getSharedPreferences("auth_prefs", Context.MODE_PRIVATE)
        prefs.edit().putString("jwt_token", token).apply()
    }
    */
}
