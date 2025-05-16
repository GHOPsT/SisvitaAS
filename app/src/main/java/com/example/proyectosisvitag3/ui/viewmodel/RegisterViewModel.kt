package com.example.proyectosisvitag3.ui.viewmodel

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay // Using delay for simulation purposes
import kotlinx.coroutines.launch

class RegisterViewModel : ViewModel() {

    // Observables for input fields
    private val _name = MutableLiveData("")
    val name: LiveData<String> = _name

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

    // --- Functions to handle input changes ---

    fun onNameChange(newName: String) {
        _name.value = newName
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
        // Simple validation logic
        val isNameValid = _name.value?.isNotBlank() ?: false
        val isEmailValid = _email.value?.let { Patterns.EMAIL_ADDRESS.matcher(it).matches() } ?: false
        val isPasswordValid = (_password.value?.length ?: 0) > 6
        val isConfirmPasswordValid = _confirmPassword.value == _password.value && _confirmPassword.value?.isNotBlank() ?: false

        _registerEnable.value = isNameValid && isEmailValid && isPasswordValid && isConfirmPasswordValid
    }

    // --- Registration Logic ---

    fun onRegisterSelected() {
        _isLoading.value = true
        _registerSuccess.value = false // Reset success state before starting

        // In a real application, you would make an API call here
        // using viewModelScope to handle coroutines lifecycle.
        viewModelScope.launch {
            // Simulate a network request delay
            delay(2000) // Simulate 2 seconds of work

            // --- Placeholder for API call and response handling ---
            // Replace this with your actual registration API call
            val registrationSuccessful = true // Simulate successful registration for now

            if (registrationSuccessful) {
                _registerSuccess.value = true
            } else {
                // Handle registration failure (e.g., show an error message)
                // _showError.value = true
                // _errorMessage.value = "Registration failed. Please try again."
            }

            _isLoading.value = false
        }
    }

    // Function to reset registerSuccess state after navigation
    fun resetRegisterSuccessState() {
        _registerSuccess.value = false
    }
}