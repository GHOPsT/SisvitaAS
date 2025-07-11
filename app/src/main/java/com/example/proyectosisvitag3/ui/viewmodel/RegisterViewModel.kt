package com.example.proyectosisvitag3.ui.viewmodel

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyectosisvitag3.data.model.request.RegisterRequest
import com.example.proyectosisvitag3.data.model.response.RegisterResponse
import com.example.proyectosisvitag3.network.ApiClient
import com.example.proyectosisvitag3.network.ApiService
import kotlinx.coroutines.launch
import retrofit2.Call

class RegisterViewModel : ViewModel() {

    private val apiService: ApiService = ApiClient.apiService
    // Observables for input fields
    private val _firstName = MutableLiveData("")
    val firstName: LiveData<String> = _firstName

    private val _lastName = MutableLiveData("")
    val lastName: LiveData<String> = _lastName

    private val _email = MutableLiveData("")
    val email: LiveData<String> = _email

    private val _password = MutableLiveData("")
    val password: LiveData<String> = _password

    private val _confirmPassword = MutableLiveData("")
    val confirmPassword: LiveData<String> = _confirmPassword

    // Observables for validation and state
    private val _registerEnable = MutableLiveData(false)
    val registerEnable: LiveData<Boolean> = _registerEnable

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    private val _registerSuccess = MutableLiveData(false)
    val registerSuccess: LiveData<Boolean> = _registerSuccess

    private val _userType = MutableLiveData("estudiante")
    val userType: LiveData<String> = _userType

    // --- Functions to handle input changes ---

    fun onUserTypeChange(newType: String) {
        _userType.value = newType
        updateRegisterButtonState()
    }

    fun onFirstNameChange(newFirstName: String) {
        _firstName.value = newFirstName
        updateRegisterButtonState()
    }

    fun onLastNameChange(newLastName: String) {
        _lastName.value = newLastName
        updateRegisterButtonState()
    }

    fun onEmailChange(newEmail: String) {
        _email.value = newEmail
        updateRegisterButtonState()
    }

    fun onPasswordChange(newPassword: String) {
        _password.value = newPassword
        updateRegisterButtonState()
    }

    fun onConfirmPasswordChange(newConfirmPassword: String) {
        _confirmPassword.value = newConfirmPassword
        updateRegisterButtonState()
    }

    // --- Validation and state update ---

    private fun updateRegisterButtonState() {
        val isFirstNameValid = _firstName.value?.isNotBlank() ?: false
        val isLastNameValid = _lastName.value?.isNotBlank() ?: false
        val isEmailValid = _email.value?.let { Patterns.EMAIL_ADDRESS.matcher(it).matches() } ?: false
        val isPasswordValid = (_password.value?.length ?: 0) > 6
        val isConfirmPasswordValid = _confirmPassword.value == _password.value && _confirmPassword.value?.isNotBlank() ?: false

        _registerEnable.value = isFirstNameValid && isLastNameValid && isEmailValid && isPasswordValid && isConfirmPasswordValid
    }

    // --- Registration Logic ---

    fun onRegisterSelected() {
        _isLoading.value = true
        viewModelScope.launch {
            try {val request = RegisterRequest(
                firstName = _firstName.value ?: "",
                lastName = _lastName.value ?: "",
                email = _email.value ?: "",
                password = _password.value ?: "",
                isSpecialist = (_userType.value == "especialista")
            )

                val response = apiService.register(request)

                if (response.isSuccessful && response.body()?.success == true) {
                    _registerSuccess.value = true
                } else {
                    // Manejar error de API o mostrar mensaje
                }
            } catch (e: Exception) {
                // error
            } finally {
                _isLoading.value = false
            }
        }
    }
}