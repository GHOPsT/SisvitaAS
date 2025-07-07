package com.example.proyectosisvitag3.ui.viewmodel

import android.content.Context
import androidx.lifecycle.*
import com.example.proyectosisvitag3.data.model.request.LoginRequest
import com.example.proyectosisvitag3.data.model.response.LoginResponse
import com.example.proyectosisvitag3.data.repository.LoginRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class LoginViewModel : ViewModel() {
    private val repository = LoginRepository()
    val loginResult = MutableLiveData<LoginResponse?>()
    val error = MutableLiveData<String?>()

    fun login(context: Context, email: String, password: String) {
        viewModelScope.launch {
            try {
                val response: Response<LoginResponse> =
                    repository.login(LoginRequest(email, password))
                if (response.isSuccessful) {
                    val loginResponse = response.body()
                    loginResult.value = loginResponse
                    loginResponse?.token?.let { saveToken(context, it) }
                } else {
                    error.value = response.message()
                }
            } catch (e: Exception) {
                error.value = e.message
            }
        }
    }

    private fun saveToken(context: Context, token: String) {
        val prefs = context.getSharedPreferences("auth_prefs", Context.MODE_PRIVATE)
        prefs.edit().putString("jwt_token", token).apply()
    }
}
