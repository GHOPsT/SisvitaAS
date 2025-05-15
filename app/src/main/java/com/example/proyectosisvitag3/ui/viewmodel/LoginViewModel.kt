package com.example.proyectosisvitag3.ui.theme.iu

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import kotlinx.coroutines.delay
import com.example.proyectosisvitag3.ui.theme.data.model.LoginRequest
import com.example.proyectosisvitag3.ui.theme.data.model.LoginResponse
import com.example.proyectosisvitag3.ui.theme.data.model.tbEstudiante
import com.example.proyectosisvitag3.ui.theme.network.ApiInstance
import com.example.proyectosisvitag3.ui.theme.data.repository.LoginRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import androidx.lifecycle.viewModelScope
import com.example.proyectosisvitag3.ui.theme.data.model.tbEspecialistas
import retrofit2.Response

class LoginViewModel : ViewModel() {

    private val repository = LoginRepository()

    private val _email = MutableLiveData<String>()
    val email: LiveData<String> = _email

    private val _password = MutableLiveData<String>()
    val password: LiveData<String> = _password

    private val _loginEnable = MutableLiveData<Boolean>()
    val loginEnable: LiveData<Boolean> = _loginEnable

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _loginSuccess = MutableLiveData<Boolean>(false)
    val loginSuccess: LiveData<Boolean> = _loginSuccess

    private val _showDialog = MutableLiveData<Boolean>()
    val showDialog: LiveData<Boolean> = _showDialog

    private val _dialogMessage = MutableLiveData<String>()
    val dialogMessage: LiveData<String> = _dialogMessage

    private val _estudiante = MutableLiveData<tbEstudiante?>()
    val estudiante: LiveData<tbEstudiante?> = _estudiante

    private val _especialistas = MutableLiveData<tbEspecialistas?>()
    val especialistas: LiveData<tbEspecialistas?> = _especialistas

    private val _isError = MutableLiveData<Boolean>()
    val isError: LiveData<Boolean> = _isError

    // Función para actualizar el estado del correo y la contraseña
    fun onLoginChanged(email: String, password: String) {
        _email.value = email
        _password.value = password
        _loginEnable.value = Patterns.EMAIL_ADDRESS.matcher(email).matches() && password.length > 6
    }

    // Función para iniciar sesión
    fun onLoginSelected() {
        _isLoading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val loginRequest = LoginRequest(_email.value ?: "", _password.value ?: "")
                val response: Response<LoginResponse> = repository.login(loginRequest)
                if (response.isSuccessful) {
                    val loginResponse = response.body()
                    if (loginResponse!= null && loginResponse.success) {
                        _estudiante.postValue(tbEstudiante(idEstudiante = loginResponse.data?.idEstudiante))
                        _loginSuccess.postValue(true)
                    } else {
                        _isError.postValue(true)
                        _loginSuccess.postValue(false)
                    }
                } else {
                    _isError.postValue(true)
                    _loginSuccess.postValue(false)
                }
                _isLoading.postValue(false)
            } catch (e: Exception) {
                _isError.postValue(true)
                _isLoading.postValue(false)
                _loginSuccess.postValue(false)
            }
        }
    }



    // Función para descartar el diálogo
    fun dismissDialog() {
        _showDialog.value = false
    }

    fun resetLoginSuccessState(){
        _loginSuccess.value = false
    }
}



/*private val _loginEnable = MutableLiveData<Boolean>()
val loginEnable: LiveData<Boolean> = _loginEnable

private val _isLoading = MutableLiveData<Boolean>()
val isLoading: LiveData<Boolean> = _isLoading

fun onLoginChanged(email: String, password: String) {
    _email.value = email
    _password.value = password
    _loginEnable.value = isValidEmail(email) && isValidPassword(password)
}

private fun isValidPassword(password: String): Boolean = password.length > 6

private fun isValidEmail(email: String): Boolean =
    Patterns.EMAIL_ADDRESS.matcher(email).matches()

suspend fun onLoginSelected() {
    _isLoading.value = true
    /*delay(4000)
    _isLoading.value = false*/
}
}

*/